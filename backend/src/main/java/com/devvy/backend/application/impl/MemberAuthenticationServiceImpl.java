package com.devvy.backend.application.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.devvy.backend.application.dto.MemberAuthenticationDto;
import com.devvy.backend.application.facade.MemberAuthenticationService;
import com.devvy.backend.common.enums.AuthorityType;
import com.devvy.backend.common.enums.AuthorizationType;
import com.devvy.backend.domain.member.Authority;
import com.devvy.backend.domain.member.MemberService;

@Service
public class MemberAuthenticationServiceImpl implements MemberAuthenticationService {

    private final MemberService memberService;

    public MemberAuthenticationServiceImpl(MemberService memberService) {this.memberService = memberService;}

    @Override
    public MemberAuthenticationDto findOneWithAuthoritiesByLogin(String login, AuthorizationType authorizationType) {
        var member = memberService.findByLoginAndAuthorizationType(login, authorizationType)
            .orElseThrow(() ->  new NoSuchElementException("Member not found"));
        return new MemberAuthenticationDto(
            member.getLogin(),
            member.getPassword(),
            member.isActivated(),
            member.getAuthorities().stream().map(Authority::getName).toList()
        );
    }
}
