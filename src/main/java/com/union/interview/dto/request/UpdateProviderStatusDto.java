package com.union.interview.dto.request;

import com.union.interview.model.enums.Status;
import jakarta.validation.constraints.NotNull;

public class UpdateProviderStatusDto {

    @NotNull(message = "Status must not be blank")
    private Status status;

    public UpdateProviderStatusDto() {
    }

    public UpdateProviderStatusDto(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
