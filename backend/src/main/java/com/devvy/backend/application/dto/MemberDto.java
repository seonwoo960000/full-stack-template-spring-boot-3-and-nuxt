package com.devvy.backend.application.dto;

import java.util.UUID;

import com.devvy.backend.common.enums.AuthorizationType;
import com.devvy.backend.common.enums.LangKey;

public record MemberDto(
        UUID id,
        String login,
        AuthorizationType authorizationType,
        String password,
        String firstName,
        String lastName,
        String email,
        boolean activated,
        LangKey langKey,
        String imageUrl
) {
   public MemberDto {
       authorizationType = authorizationType != null ? authorizationType : AuthorizationType.NONE;
       langKey = langKey != null ? langKey : LangKey.EN;
   }
}
