package com.yoonmin.board.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private String username;
    @Column(length = 100, nullable = false)
    private Long hits;
    @Column(length = 10, nullable = false)
    private String password;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;



    @Builder
    public PostEntity(Long id, String title, String content, String username, Long hits, LocalDateTime createdAt, LocalDateTime updatedAt, String password) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.password= password;
    }
}
