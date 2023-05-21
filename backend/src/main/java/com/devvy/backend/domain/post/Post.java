package com.devvy.backend.domain.post;

import java.util.UUID;

import com.devvy.backend.domain.common.AbstractAuditingEntityWithUUID;
import com.devvy.backend.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post extends AbstractAuditingEntityWithUUID {

    @Column(nullable = false, columnDefinition = "binary(16)")
    private UUID memberId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
               "memberId=" + memberId +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
