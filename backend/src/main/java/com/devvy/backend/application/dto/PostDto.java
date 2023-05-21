package com.devvy.backend.application.dto;

import java.util.UUID;

public record PostDto(
    UUID id,
    UUID memberId,
    String title,
    String content
) {
}
