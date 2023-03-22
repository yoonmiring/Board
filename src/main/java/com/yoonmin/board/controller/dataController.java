package com.yoonmin.board.controller;

import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.yoonmin.board.domain.dto.PostDto;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value="/")
public class dataController {
    @Autowired
    private PostService postService;

    @PostMapping("board/posts/new")
    public ResponseEntity<Long> savePost(@RequestBody PostDto postDto) {
        Long postId = postService.savePost(postDto);
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }

}
