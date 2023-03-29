package com.yoonmin.board.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
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

    @Column(length = 100)
    private long hits = 0L;
    @Column(length = 10, nullable = false)
    private String password;

    @Column(name = "created_at",  nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;



    @Builder
    public PostEntity(Long id, String title, String content, String username, Long hits, LocalDateTime createdAt, LocalDateTime updatedAt, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.hits = hits;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.password = password;
    }

    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeContents(String content) {
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
