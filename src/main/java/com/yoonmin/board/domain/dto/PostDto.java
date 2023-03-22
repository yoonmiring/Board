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
    private String authorUsername;

    public String getTitle() {
        return title;
    }
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long hits;

    public PostEntity toEntity(){
        PostEntity postEntity = PostEntity.builder()
                .id(id)
                .authorUsername(authorUsername)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .hits(hits)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(Long id, String title, String content, String authorUsername, LocalDateTime createdAt, LocalDateTime updatedAt, Long hits) {
        this.id = id;
        this.authorUsername = authorUsername;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.hits = hits;
    }
}
