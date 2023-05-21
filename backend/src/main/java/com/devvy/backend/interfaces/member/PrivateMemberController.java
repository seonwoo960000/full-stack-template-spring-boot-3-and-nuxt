package com.devvy.backend.interfaces.member;

import static com.devvy.backend.infrastructure.config.SecurityConfiguration.SECURITY_CONFIG_NAME;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/private/members")
@SecurityRequirement(name = SECURITY_CONFIG_NAME)
public class PrivateMemberController {

    @GetMapping
    public String test() {
        return "hello";
    }
}
