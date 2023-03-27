package com.yoonmin.board.domain.dto;

import com.yoonmin.board.domain.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {
    private Long id;

    private String title;

    private String content;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long hits;

    private String password;


    public PostEntity toEntity(){
        PostEntity postEntity = PostEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .username(username)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .hits(hits)
                .password(password)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(Long id, String title, String content, String username, LocalDateTime createdAt, LocalDateTime updatedAt, long hits, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.hits = hits;
        this.password = password;
    }
}
