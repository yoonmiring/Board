package com.yoonmin.board.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yoonmin.board.domain.entity.CommentEntity;
import com.yoonmin.board.domain.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private String username;
    private PostEntity postId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private String password;

    public CommentEntity toEntity(){
        CommentEntity.CommentEntityBuilder builder = CommentEntity.builder();
        builder.id(id);
        builder.content(content);
        builder.username(username);
        builder.postId(postId);
        builder.createdAt(createdAt);
        builder.password(password);
        CommentEntity commentEntity = builder.build();
        return commentEntity;
    }
    @Builder
    public CommentDto(Long id, String content, PostEntity postId, String username, LocalDateTime createdAt, String password){
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.username = username;
        this.createdAt = createdAt;
        this.password = password;
    }
}
