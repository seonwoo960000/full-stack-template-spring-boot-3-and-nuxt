package com.devvy.backend.domain.post;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {this.postRepository = postRepository;}

    @Transactional(readOnly = true)
    public Optional<Post> findById(UUID id) {
        return postRepository.findById(id);
    }

    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public void delete(UUID id) {
        postRepository.deleteById(id);
    }
}
