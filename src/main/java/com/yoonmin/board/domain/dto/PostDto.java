package com.yoonmin.board.domain.dto;

import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {

//    private Long id;
//    private String title;
//    private String content;
//    private UserEntity authorUsername;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//       public PostEntity toEntity() {
//        PostEntity postEntity = PostEntity.builder()
//                .id(id)
//                .authorUsername(authorUsername)
//                .title(title)
//                .content(content)
//                .build();
//        return postEntity;
//    }
//    @Builder
//    public PostDto(Long id, String title, String content, UserEntity authorUsername, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.id = id;
//        this.authorUsername = authorUsername;
//        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//
//    private Long id;
//    private String title;
//    private UserEntity authorUsername;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//
//    public PostEntity toEntity(){
//        return PostEntity.builder()
//                .id(id)
//                .title(title)
//                .authorUsername(authorUsername)
//                .createdAt(createdAt)
//                .updatedAt(updatedAt)
//                .build();
//    }
private Long id;
    private String writer;

    public String getTitle() {
        return title;
    }
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostEntity toEntity(){
        PostEntity postEntity = PostEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
