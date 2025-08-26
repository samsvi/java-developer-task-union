package com.union.interview.service;

import com.union.interview.dto.response.ContractResponseDto;
import com.union.interview.mapper.ContractMapper;
import com.union.interview.model.Contract;
import com.union.interview.repository.ContractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    private final ContractMapper contractMapper;

    public ContractService(ContractRepository contractRepository, ContractMapper contractMapper) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }

    public ContractResponseDto getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        return contractMapper.toDto(contract);
    }

}
