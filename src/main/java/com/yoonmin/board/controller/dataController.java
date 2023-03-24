package com.yoonmin.board.controller;

import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.yoonmin.board.domain.dto.PostDto;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping(value="/board")
public class dataController {
    @Autowired
    private PostService postService;

//    main Board
    @GetMapping(value = "/board", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDto>> list() throws Exception {
        List<PostDto> postList = postService.getPostlist();
        return new ResponseEntity<List<PostDto>>(postList, HttpStatus.OK);
    }


    //작성글 등록
    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public ResponseEntity<Long> savePost(@RequestBody PostDto postDto) {
        Long postId = postService.savePost(postDto);
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }

    //글 수정하기
    @RequestMapping(value = "/posts/{boardId}", method = RequestMethod.PUT)
    public String updatePost(PostDto postDTO, @PathVariable Long boardId) throws Exception {
        postService.savePost(postDTO);
        return "redirect:/board";
    }

    //글 삭제하기
    @DeleteMapping(value = "/posts/{boardId}")
    public String deletePost(@PathVariable("boardId") Long boardId) throws Exception {
        postService.deletePost(boardId);
        return "redirect:/board";
    }
}
