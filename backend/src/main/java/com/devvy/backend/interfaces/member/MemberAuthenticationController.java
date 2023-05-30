package com.devvy.backend.interfaces.member;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devvy.backend.infrastructure.security.CustomOauth2UserService;
import com.devvy.backend.infrastructure.security.jwt.JWTFilter;
import com.devvy.backend.infrastructure.security.jwt.TokenProvider;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api/v1/public")
public class MemberAuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CustomOauth2UserService customOauth2UserService;

    public MemberAuthenticationController(TokenProvider tokenProvider,
                                          AuthenticationManagerBuilder authenticationManagerBuilder,
                                          CustomOauth2UserService customOauth2UserService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.customOauth2UserService = customOauth2UserService;
    }

    @GetMapping("/oauth2/providers")
    public ResponseEntity<Map<String, String>> oauth2Providers() {
        return ResponseEntity.ok(customOauth2UserService.getOAuth2RedirectUrls());
    }

    @GetMapping("/oauth2/success")
    public ResponseEntity<String> success() {
        return ResponseEntity.ok("success");
    }

    @GetMapping("/oauth2/fail")
    public ResponseEntity<String> fail() {
        return ResponseEntity.ok("fail");
    }

    @PostMapping("/jwt/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody MemberLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginRequest.username(),
            loginRequest.password()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(
            authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginRequest.rememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
