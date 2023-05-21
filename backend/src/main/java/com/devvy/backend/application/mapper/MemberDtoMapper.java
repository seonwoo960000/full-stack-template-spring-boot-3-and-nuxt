package com.devvy.backend.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.devvy.backend.application.dto.MemberDto;
import com.devvy.backend.domain.member.Member;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberDtoMapper {

    Member toMember(MemberDto memberDto);

    MemberDto fromMember(Member member);
}
