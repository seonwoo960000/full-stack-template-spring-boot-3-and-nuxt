package com.devvy.backend.domain.post;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
