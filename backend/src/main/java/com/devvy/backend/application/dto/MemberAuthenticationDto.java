package com.devvy.backend.application.dto;

import java.util.List;

import com.devvy.backend.common.enums.AuthorityType;

public record MemberAuthenticationDto(
    String login,
    String password,
    boolean activated,
    List<AuthorityType> authorities
) {
}
