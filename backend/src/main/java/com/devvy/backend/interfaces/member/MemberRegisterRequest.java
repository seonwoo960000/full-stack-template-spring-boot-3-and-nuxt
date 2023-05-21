package com.devvy.backend.interfaces.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MemberRegisterRequest(
    @Size(max = 50)
    @NotNull(message = "login should not be null")
    @Schema(example = "devvy@gmail.com", maxLength = 50)
    String login,
    @Size(max = 50)
    @NotNull(message = "password should not be null")
    @Schema(example = "password", maxLength = 50)
    String password,
    @Size(max = 50)
    @NotNull(message = "firstName should not be null")
    @Schema(example = "Seon Woo", maxLength = 50)
    String firstName,
    @Size(max = 50)
    @NotNull(message = "lastName should not be null")
    @Schema(example = "Kim", maxLength = 50)
    String lastName,
    @Size(max = 255)
    @NotNull(message = "email should not be null")
    @Schema(example = "devvy@gmail.com", maxLength = 255)
    String email,

    @Size(max = 255)
    @NotNull(message = "imageUrl should not be null")
    @Schema(example = "http://your-image-url.com")
    String imgUrl
) {
}
