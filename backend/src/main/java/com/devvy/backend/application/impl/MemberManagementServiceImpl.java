package com.devvy.backend.application.impl;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devvy.backend.application.dto.MemberDto;
import com.devvy.backend.application.facade.MemberManagementService;
import com.devvy.backend.application.mapper.MemberDtoMapper;
import com.devvy.backend.common.enums.AuthorizationType;
import com.devvy.backend.domain.member.MemberService;

@Service
public class MemberManagementServiceImpl implements MemberManagementService {

    private final MemberService memberService;
    private final MemberDtoMapper memberDtoMapper;

    private final PasswordEncoder passwordEncoder;

    public MemberManagementServiceImpl(MemberService memberService, MemberDtoMapper memberDtoMapper,
                                       PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.memberDtoMapper = memberDtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findById(UUID id) {
        var member = memberService.findById(id).orElseThrow(
            () -> new NoSuchElementException("Member not found"));
        return memberDtoMapper.fromMember(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findByName(String name, AuthorizationType authorizationType) {
        var member = memberService.findByLoginAndAuthorizationType(
            name, authorizationType).orElseThrow(
            () -> new NoSuchElementException("Member not found"));
        return memberDtoMapper.fromMember(member);
    }

    @Override
    @Transactional
    public MemberDto registerMember(MemberDto memberDto) {
        var member = memberDtoMapper.toMember(memberDto);
        member.setPassword(passwordEncoder.encode(memberDto.password()));
        member.setActivated(true);
        return memberDtoMapper.fromMember(memberService.save(member));
    }
}
