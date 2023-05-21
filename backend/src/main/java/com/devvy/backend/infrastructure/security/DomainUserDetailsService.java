package com.devvy.backend.infrastructure.security;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devvy.backend.application.dto.MemberAuthenticationDto;
import com.devvy.backend.application.facade.MemberAuthenticationService;
import com.devvy.backend.common.enums.AuthorizationType;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final MemberAuthenticationService memberAuthenticationService;

    public DomainUserDetailsService(MemberAuthenticationService memberAuthenticationService) {
        this.memberAuthenticationService = memberAuthenticationService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        var memberAuthenticationDto = memberAuthenticationService
            .findOneWithAuthoritiesByLogin(lowercaseLogin, AuthorizationType.NONE);
        return createSpringSecurityUser(lowercaseLogin, memberAuthenticationDto);
    }

    private User createSpringSecurityUser(String lowercaseLogin, MemberAuthenticationDto member) {
        if (!member.activated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = member
            .authorities()
            .stream()
            .map(Enum::name)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        return new User(member.login(), member.password(), grantedAuthorities);
    }
}
