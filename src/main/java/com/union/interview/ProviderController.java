package com.union.interview;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @PostMapping("/contracts")
    public ResponseEntity<String> createContract(@RequestBody Map<String, Object> request) {
        try {
           
            String providerIco = (String) request.get("providerIco");
            if (providerIco == null || providerIco.length() != 8) {
                return ResponseEntity.badRequest().body("Invalid ICO format");
            }
            
           
            String serviceCode = (String) request.get("serviceCode");
            int quantity = (Integer) request.get("quantity");
            
            
            String sql = "SELECT unit_price FROM services WHERE code = '" + serviceCode + "'";
            Double unitPrice = jdbcTemplate.queryForObject(sql, Double.class);
            
            if (unitPrice == null) {
                return ResponseEntity.badRequest().body("Service not found: " + serviceCode);
            }
            
            double contractValue = unitPrice * quantity;
            
            
            
            String providerIdSql = "SELECT id FROM providers WHERE ico = '" + providerIco + "'";
            BigInteger providerId = jdbcTemplate.queryForObject(providerIdSql, BigInteger.class);
            
            if (providerId == null) {
                return ResponseEntity.badRequest().body("Provider not found: " + providerIco);
            }
           
            String serviceIdSql = "SELECT id FROM services WHERE code = '" + serviceCode + "'";
            BigInteger serviceId = jdbcTemplate.queryForObject(serviceIdSql, BigInteger.class);
            
            if (serviceId == null) {
                return ResponseEntity.badRequest().body("Service not found: " + providerIco);
            }
          
            String insertContractSql = "INSERT INTO contracts (provider_id, service_id,  quantity, total_price, status) " +
                                     "VALUES (" + providerId + ", " + serviceId  + ", " + quantity + ", "+contractValue+" , 'ACTIVE')";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                    insertContractSql,
                    Statement.RETURN_GENERATED_KEYS
                );
                return ps;
            }, keyHolder);
            
            long contractId = keyHolder.getKey().longValue();    
            
            return ResponseEntity.ok("Contract created with ID: " + contractId);
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/contracts/{id}")
    public ResponseEntity<Map<String, Object>> getContract(@PathVariable long id) {
        try {
            
           
            String sql = "SELECT c.id AS contract_id, "+
                            " p.name AS provider_name,"+
                            " p.ico AS provider_ico,"+
                            " s.name AS service_name,"+
                             " s.code AS service_code,"+
                            " c.quantity,"+
                            " c.total_price,"+
                            " c.status"+
                            " FROM contracts c"+
                            " JOIN providers p ON c.provider_id = p.id"+
                            " JOIN services s ON c.service_id = s.id"+
                            " WHERE c.id = " + id;
            Map<String, Object> contract = jdbcTemplate.queryForMap(sql);
            
           
            
            return ResponseEntity.ok(contract);
            
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
            
        }
    }
    
    @PutMapping("/providers/{ico}/status")
    public ResponseEntity<String> updateProviderStatus(@PathVariable String ico, @RequestBody Map<String, String> request) {
        try {
            String newStatus = request.get("status");
            String providerIdSql = "SELECT id FROM providers WHERE ico = '" + ico + "'";
            BigInteger providerId = jdbcTemplate.queryForObject(providerIdSql, BigInteger.class);
            
            if (providerId == null) {
                return ResponseEntity.badRequest().body("Provider not found: " + ico);
            }
           
            if (!"ACTIVE".equals(newStatus) && !"SUSPENDED".equals(newStatus) && !"TERMINATED".equals(newStatus)) {
                return ResponseEntity.badRequest().body("Invalid status");
            }
           
                String updateContractsSql = "UPDATE contracts SET status = '"+newStatus+"' WHERE provider_id = '" + 
                                          providerId + "'";
                jdbcTemplate.update(updateContractsSql);
            
        
            String updateProviderSql = "UPDATE providers SET status = '" + newStatus + "' WHERE ico = '" + ico + "'";
            int updated = jdbcTemplate.update(updateProviderSql);
            
            if (updated == 0) {
                return ResponseEntity.notFound().build();
            }
            
            return ResponseEntity.ok("Provider status updated");
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}