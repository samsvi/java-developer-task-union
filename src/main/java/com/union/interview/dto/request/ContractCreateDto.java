package com.union.interview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContractCreateDto {

    @NotBlank(message = "ICO must not be null")
    @Size(min = 8, max = 8, message = "Invalid ICO format")
    private String providerIco;

    @NotBlank(message = "Service code must not be null")
    private String serviceCode;

    @NotNull(message = "Quantity must not be null")
    private Integer quantity;

    public ContractCreateDto(String providerIco, String serviceCode, Integer quantity) {
        this.providerIco = providerIco;
        this.serviceCode = serviceCode;
        this.quantity = quantity;
    }

    public ContractCreateDto() {
    }

    public String getProviderIco() {
        return providerIco;
    }

    public void setProviderIco(String providerIco) {
        this.providerIco = providerIco;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
