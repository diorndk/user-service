package com.ninety9group.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private boolean result;
    private Object errors;

    public ErrorResponse(Object errors) {
        this.result = false;
        this.errors = errors;
    }
}
