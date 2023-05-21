package com.devvy.backend.interfaces.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record MemberLoginRequest(
    @NotNull(message = "username should not be null")
    @Schema(example = "devvy@gmail.com")
    String username,
    @NotNull(message = "password should not be null")
    @Schema(example = "password@!@#")
    String password,
    @Schema(example = "false", description = "To create rememberMe token, set this field to true")
    boolean rememberMe
) {
    public MemberLoginRequest {
        rememberMe = true;
    }
}
