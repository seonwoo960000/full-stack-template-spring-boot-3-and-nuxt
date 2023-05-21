package com.devvy.backend.application.facade;

import java.util.UUID;

import com.devvy.backend.application.dto.PostDto;

public interface MemberPostingService {

    PostDto findById(UUID id);

    PostDto registerPost(PostDto postDto);

    PostDto updatePost(PostDto postDto);

    void deletePost(UUID id);
}
