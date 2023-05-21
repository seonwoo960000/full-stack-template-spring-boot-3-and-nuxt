package com.devvy.backend.interfaces.error;

import java.io.Serializable;
import java.util.Optional;

public record ErrorResponse(
        String request,
        String message
        ) implements Serializable {
    private static final long serialVersionUID = 1L;

    Optional<ErrorResponse> toOptional() {
        return Optional.of(this);
    }
}
