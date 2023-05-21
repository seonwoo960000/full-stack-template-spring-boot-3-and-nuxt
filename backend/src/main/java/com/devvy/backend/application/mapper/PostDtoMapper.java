package com.devvy.backend.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.devvy.backend.application.dto.PostDto;
import com.devvy.backend.domain.post.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostDtoMapper {

    Post toPost(PostDto postDto);

    PostDto fromPost(Post post);
}
