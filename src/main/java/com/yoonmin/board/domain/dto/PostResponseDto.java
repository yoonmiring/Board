//package com.yoonmin.board.domain.dto;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Getter
//@NoArgsConstructor
//public class PostResponseDto {
//    private Long id;
//    private String title;
//    private String content;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private Long hits;
//    private PostUser authorUsername;
//
//    public PostResponseDto(PostDto postdto){
//        this.id = postdto.getId();
//        this.title= postdto.getTitle();
//        this.content=postdto.getContent();
//        this.authorUsername= PostUser.builder()
//                .username(postdto.getUser().getUsername)
//                .password(postdto.getUser().getPassword)
//                .build();
//    }
//    @Getter
//    static class PostUser{
//        private String username;
//        private String password;
//
//        @Builder
//        public PostUser(String username, String password){
//            this.username = username;
//            this.password = password;
//        }
//    }
//
//}
