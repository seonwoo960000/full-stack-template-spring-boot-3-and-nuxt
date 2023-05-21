package com.devvy.backend.interfaces.member;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberRegisterResponse(
    @Schema(example = "5950863e-d40f-11ed-a61b-7f379855e063")
    UUID id,
    @Schema(example = "devvy@gmail.com", maxLength = 50)
    String login,
    @Schema(example = "password", maxLength = 50)
    String password,
    @Schema(example = "Seon Woo", maxLength = 50)
    String firstName,
    @Schema(example = "Kim", maxLength = 50)
    String lastName,
    @Schema(example = "devvy@gmail.com", maxLength = 255)
    String email,
    @Schema(example = "http://your-image-url.com")
    String imgUrl
) {
}
