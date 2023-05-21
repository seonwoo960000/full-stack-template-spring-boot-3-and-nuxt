package com.devvy.backend.interfaces.post;

import static com.devvy.backend.infrastructure.config.SecurityConfiguration.SECURITY_CONFIG_NAME;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/private/posts")
@SecurityRequirement(name = SECURITY_CONFIG_NAME)
public class PrivatePostController {
}
