package com.yoonmin.board.domain.dto;

import com.yoonmin.board.domain.entity.PostEntity;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
public class PostDto {
    private Long id;

    private String title;

    private String content;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long hits;

    private String password;


//    public PostEntity toEntity(){
//        PostEntity postEntity = PostEntity.builder()
//                .id(id)
//                .title(title)
//                .content(content)
//                .username(username)
//                .createdAt(createdAt)
//                .updatedAt(updatedAt)
//                .hits(hits)
//                .password(password)
//                .build();
//        return postEntity;
//    }
    public PostEntity toEntity(){
        PostEntity.PostEntityBuilder builder = PostEntity.builder();
        builder.id(id);
        builder.title(title);
        builder.content(content);
        builder.username(username);
        builder.createdAt(createdAt);
        builder.updatedAt(updatedAt);
        builder.hits(hits);
        builder.password(password);
        PostEntity postEntity = builder.build();
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
