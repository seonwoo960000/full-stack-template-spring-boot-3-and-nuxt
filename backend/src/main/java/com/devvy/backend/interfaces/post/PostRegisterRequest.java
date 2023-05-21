package com.devvy.backend.interfaces.post;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostRegisterRequest(
    @NotNull(message = "memberId should not be null")
    @Schema(example = "5950863e-d40f-11ed-a61b-7f379855e063")
    UUID memberId,
    @Size(max = 50)
    @NotBlank(message = "title should not be blank")
    @Schema(example = "title")
    String title,
    @Size(max = 1000)
    @NotBlank(message = "content should not be blank")
    @Schema(example = "This is the content, please fill in the content")
    String content
) {
}
