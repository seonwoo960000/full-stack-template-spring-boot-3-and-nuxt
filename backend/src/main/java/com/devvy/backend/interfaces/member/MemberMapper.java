package com.devvy.backend.interfaces.member;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.devvy.backend.application.dto.MemberDto;
import com.devvy.backend.interfaces.member.MemberRegisterRequest;
import com.devvy.backend.interfaces.member.MemberRegisterResponse;
import com.devvy.backend.interfaces.member.MemberResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    MemberResponse fromMemberDtoToMemberResponse(MemberDto memberDto);

    MemberRegisterResponse fromMemberDtoToMemberRegisterResponse(MemberDto memberDto);

    MemberDto fromMemberRegisterRequestToMemberDto(MemberRegisterRequest memberRegisterRequest);
}
