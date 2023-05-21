package com.devvy.backend.application.facade;

import java.util.UUID;

import com.devvy.backend.application.dto.MemberDto;
import com.devvy.backend.common.enums.AuthorizationType;

public interface MemberManagementService {

    MemberDto findById(UUID id);

    MemberDto findByName(String name, AuthorizationType authorizationType);

    MemberDto registerMember(MemberDto memberDto);
}
