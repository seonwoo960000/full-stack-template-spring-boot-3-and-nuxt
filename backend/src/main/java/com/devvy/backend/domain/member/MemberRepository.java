package com.devvy.backend.domain.member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devvy.backend.common.enums.AuthorizationType;

interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByLoginAndAuthorizationType(String login, AuthorizationType authorizationType);
}
