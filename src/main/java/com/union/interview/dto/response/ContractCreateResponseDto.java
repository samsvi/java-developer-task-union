package com.union.interview.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractCreateResponseDto {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("MESSAGE")
    private String message;

    public ContractCreateResponseDto() {
    }

    public ContractCreateResponseDto(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
