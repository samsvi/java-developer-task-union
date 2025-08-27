package com.union.interview.service;

import com.union.interview.dto.request.UpdateProviderStatusDto;
import com.union.interview.dto.response.UpdateProviderResponseDto;
import com.union.interview.exception.ResourceNotFoundException;
import com.union.interview.model.Provider;
import com.union.interview.repository.ProviderRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public UpdateProviderResponseDto updateProviderStatus(String ico, UpdateProviderStatusDto updateStatusDto) {

        Provider provider = providerRepository.findByIco(ico)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found: " + ico));

        provider.setStatus(updateStatusDto.getStatus());

        Provider updatedProvider = providerRepository.save(provider);

        return new UpdateProviderResponseDto(updatedProvider.getId(), "Provider updated");
    }
}
