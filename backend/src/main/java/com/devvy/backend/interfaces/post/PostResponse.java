package com.devvy.backend.interfaces.post;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostResponse(
    @Schema(example = "4340863e-d40f-122d-a61b-7f379855e063")
    UUID id,
    @Schema(example = "5950863e-d40f-11ed-a61b-7f379855e063")
    UUID memberId,
    @Schema(example = "title")
    String title,
    @Schema(example = "This is the content, please fill in the content")
    String content
) {
}
