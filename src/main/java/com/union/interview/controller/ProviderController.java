package com.union.interview.controller;

import com.union.interview.dto.request.UpdateProviderStatusDto;
import com.union.interview.dto.response.UpdateProviderResponseDto;
import com.union.interview.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PutMapping("{ico}/status")
    public ResponseEntity<UpdateProviderResponseDto> updateProviderStatus(@PathVariable("ico") String ico, @Valid @RequestBody UpdateProviderStatusDto updateStatusDto) {
        UpdateProviderResponseDto updateProviderResponseDto = providerService.updateProviderStatus(ico, updateStatusDto);

        return ResponseEntity.ok(updateProviderResponseDto);
    }
}

