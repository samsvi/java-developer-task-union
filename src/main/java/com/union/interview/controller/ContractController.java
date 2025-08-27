package com.union.interview.controller;

import com.union.interview.dto.request.ContractCreateDto;
import com.union.interview.dto.response.ContractCreateResponseDto;
import com.union.interview.dto.response.ContractResponseDto;
import com.union.interview.service.ContractService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ContractCreateResponseDto> createContract(@Valid @RequestBody ContractCreateDto contractCreateDto) {
        ContractCreateResponseDto contractResponse = contractService.createContract(contractCreateDto);

        return ResponseEntity.ok(contractResponse);
    }
}
