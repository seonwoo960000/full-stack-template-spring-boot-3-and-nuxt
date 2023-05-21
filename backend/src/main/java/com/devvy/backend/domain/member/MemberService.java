package com.devvy.backend.domain.member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devvy.backend.common.enums.AuthorizationType;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {this.memberRepository = memberRepository;}

    @Transactional(readOnly = true)
    public Optional<Member> findById(UUID id) {
        return memberRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByLoginAndAuthorizationType(String login, AuthorizationType authorizationType) {
        return memberRepository.findByLoginAndAuthorizationType(login, authorizationType);
    }

    @Transactional
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    public void delete(UUID id) {
        memberRepository.deleteById(id);
    }
}
