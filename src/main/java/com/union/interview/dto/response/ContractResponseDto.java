package com.union.interview.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.union.interview.model.enums.Status;

import java.math.BigDecimal;

public class ContractResponseDto {

    @JsonProperty("CONTRACT_ID")
    private Long id;

    @JsonProperty("PROVIDER_NAME")
    private String providerName;

    @JsonProperty("PROVIDER_ICO")
    private String providerIco;

    @JsonProperty("SERVICE_NAME")
    private String serviceName;

    @JsonProperty("SERVICE_CODE")
    private String serviceCode;

    @JsonProperty("QUANTITY")
    private Integer quantity;

    @JsonProperty("TOTAL_PRICE")
    private BigDecimal price;

    @JsonProperty("STATUS")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderIco() {
        return providerIco;
    }

    public void setProviderIco(String providerIco) {
        this.providerIco = providerIco;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
