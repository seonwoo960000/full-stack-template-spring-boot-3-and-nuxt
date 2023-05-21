package com.devvy.backend.application.impl;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devvy.backend.application.dto.PostDto;
import com.devvy.backend.application.facade.MemberPostingService;
import com.devvy.backend.application.mapper.PostDtoMapper;
import com.devvy.backend.domain.member.MemberService;
import com.devvy.backend.domain.post.PostService;

@Service
public class MemberPostingServiceImpl implements MemberPostingService {

    private final MemberService memberService;
    private final PostService postService;

    private final PostDtoMapper postDtoMapper;

    public MemberPostingServiceImpl(MemberService memberService, PostService postService,
                                    PostDtoMapper postDtoMapper) {
        this.memberService = memberService;
        this.postService = postService;
        this.postDtoMapper = postDtoMapper;
    }

    @Override
    public PostDto findById(UUID id) {
        var post = postService.findById(id).orElseThrow(() -> new NoSuchElementException("No post found"));
        return postDtoMapper.fromPost(post);
    }

    @Override
    public PostDto registerPost(PostDto postDto) {
        var savedPost = postService.save(postDtoMapper.toPost(postDto));
        return postDtoMapper.fromPost(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        var updatedPost = postService.save(postDtoMapper.toPost(postDto));
        return postDtoMapper.fromPost(updatedPost);
    }

    @Override
    public void deletePost(UUID id) {
        postService.delete(id);
    }
}
