package com.yoonmin.board.controller;

import com.yoonmin.board.domain.dto.BoardDto;
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

    //main Board data
    @GetMapping(value = "/board", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoardDto>> list() throws Exception {
        List<BoardDto> postList = postService.getPostlist();
        return new ResponseEntity<List<BoardDto>>(postList, HttpStatus.OK);
    }

    //작성글 등록
    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public ResponseEntity<Long> savePost(@RequestBody PostDto postDto) throws Exception {
        if (postDto.getContent() == null) {
            postDto.setContent("");
        }
        Long postId = postService.savePost(postDto);
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }

    //글 상세 불러오기
    @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> getPost(@RequestBody Long boardId) throws Exception {
        PostDto postDTO = postService.getPost(boardId);
        return new ResponseEntity<PostDto>(postDTO, HttpStatus.OK);
    }

    //글 수정하기
    @PutMapping(value = "/posts/edit/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDTO, @PathVariable() Long postId) throws Exception {
        PostDto updatePost = postService.updatePost(postId, postDTO);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

//    @RequestMapping(value = "/posts/edit/{boardId}", method = RequestMethod.PUT)
//    public ResponseEntity<Long> updatePost(@RequestBody PostDto postDto) throws Exception {
//        Long postId = postService.savePost(postDto);
//        return new ResponseEntity<>(postId, HttpStatus.CREATED);
//    }



    //글 삭제하기
    @DeleteMapping(value = "/posts/{boardId}")
    public String deletePost(@PathVariable("boardId") Long boardId) throws Exception {
        postService.deletePost(boardId);
        return "redirect:/board";
    }
}
