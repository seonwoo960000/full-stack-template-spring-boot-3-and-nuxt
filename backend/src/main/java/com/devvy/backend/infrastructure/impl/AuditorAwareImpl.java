package com.devvy.backend.infrastructure.impl;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: implement custom logic for @CreatedBy, @LastModifiedBy
        return Optional.of("SYSTEM");
    }
}
