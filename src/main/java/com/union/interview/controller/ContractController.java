package com.union.interview.controller;

import com.union.interview.dto.response.ContractResponseDto;
import com.union.interview.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ContractResponseDto> getContractById(@PathVariable Long id) {

        ContractResponseDto contractResponseDto = contractService.getContractById(id);

        return ResponseEntity.ok(contractResponseDto);
    }
}
