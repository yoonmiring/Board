package com.yoonmin.board.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String authorUsername;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 100, nullable = false)
    private Long hits;

    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",updatable = true)
    private LocalDateTime updatedAt;
    @Builder
    public PostEntity(Long id, String title, String content, String authorUsername, Long hits, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.authorUsername = authorUsername;
        this.title = title;
        this.content = content;
        this.hits= hits;
        this.createdAt= createdAt;
        this.updatedAt=updatedAt;
    }
}
