package com.devvy.backend.interfaces.post;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devvy.backend.application.facade.MemberPostingService;

import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1/public/posts")
public class PublicPostController {

    private final MemberPostingService memberPostingService;
    private final PostMapper postMapper;

    public PublicPostController(MemberPostingService memberPostingService, PostMapper postMapper) {
        this.memberPostingService = memberPostingService;
        this.postMapper = postMapper;
    }

    @GetMapping("/{id}")
    public PostResponse findById(
        @Schema(example = "0188390a-057f-fd1b-0c22-dc28eb3b0c54")
        @PathVariable UUID id) {
        var post = memberPostingService.findById(id);
        return postMapper.fromPostDtotoPostResponse(post);
    }

    @PostMapping
    public PostResponse registerPost(@RequestBody PostRegisterRequest postRegisterRequest) {
        var savedPost = memberPostingService.registerPost(
            postMapper.fromPostRegisterRequestToPostDto(postRegisterRequest));
        return postMapper.fromPostDtotoPostResponse(savedPost);
    }

    @PutMapping
    public PostResponse updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
        var updatedPost = memberPostingService.updatePost(
            postMapper.fromPostUpdateRequestToPostDto(postUpdateRequest));
        return postMapper.fromPostDtotoPostResponse(updatedPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(
        @Schema(example = "0188390a-057f-fd1b-0c22-dc28eb3b0c54")
        @PathVariable UUID id) {
        memberPostingService.deletePost(id);
    }
}
