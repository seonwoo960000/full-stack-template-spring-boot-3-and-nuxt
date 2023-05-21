package com.devvy.backend.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devvy.backend.common.enums.AuthorityType;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityType> {
}
