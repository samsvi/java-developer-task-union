package com.union.interview.service;

import com.union.interview.dto.request.ContractCreateDto;
import com.union.interview.dto.response.ContractCreateResponseDto;
import com.union.interview.dto.response.ContractResponseDto;
import com.union.interview.exception.ResourceNotFoundException;
import com.union.interview.mapper.ContractMapper;
import com.union.interview.model.Contract;
import com.union.interview.model.Provider;
import com.union.interview.model.Service;
import com.union.interview.model.enums.Status;
import com.union.interview.repository.ContractRepository;
import com.union.interview.repository.ProviderRepository;
import com.union.interview.repository.ServiceRepository;

import java.math.BigDecimal;


@org.springframework.stereotype.Service
public class ContractService {

    private final ContractRepository contractRepository;

    private final ServiceRepository serviceRepository;

    private final ProviderRepository providerRepository;

    private final ContractMapper contractMapper;

    public ContractService(ContractRepository contractRepository, ServiceRepository serviceRepository, ProviderRepository providerRepository, ContractMapper contractMapper) {
        this.contractRepository = contractRepository;
        this.serviceRepository = serviceRepository;
        this.providerRepository = providerRepository;
        this.contractMapper = contractMapper;
    }

    public ContractResponseDto getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found: " + id));

        return contractMapper.toDto(contract);
    }

    public ContractCreateResponseDto createContract(ContractCreateDto contractDto) {

        Service service = serviceRepository.findByCode(contractDto.getServiceCode())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found: " + contractDto.getServiceCode()));

        BigDecimal totalValue = service.getUnitPrice().multiply(BigDecimal.valueOf(contractDto.getQuantity()));

        Provider provider = providerRepository.findByIco(contractDto.getProviderIco())
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found: " + contractDto.getProviderIco()));

        Contract contract = new Contract(provider, service, contractDto.getQuantity(), totalValue, Status.ACTIVE);
        Contract savedContract = contractRepository.save(contract);

        return new ContractCreateResponseDto(
                savedContract.getId(),
                "Contract created successfully"
        );
    }

}
