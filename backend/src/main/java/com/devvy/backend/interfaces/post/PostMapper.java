package com.devvy.backend.interfaces.post;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.devvy.backend.application.dto.PostDto;
import com.devvy.backend.interfaces.post.PostRegisterRequest;
import com.devvy.backend.interfaces.post.PostResponse;
import com.devvy.backend.interfaces.post.PostUpdateRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    PostDto fromPostRegisterRequestToPostDto(PostRegisterRequest postRegisterRequest);

    PostDto fromPostUpdateRequestToPostDto(PostUpdateRequest postUpdateRequest);

    PostResponse fromPostDtotoPostResponse(PostDto postDto);
}
