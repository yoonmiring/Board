package com.yoonmin.board.controller;


import com.yoonmin.board.domain.dto.BoardDto;
import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.domain.repository.PostRepository;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.yoonmin.board.domain.dto.PostDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class dataController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    //main Board data
    @GetMapping(value = "/board", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BoardDto>> list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoardDto> postList = postService.getPostlist(pageable);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
    //작성글 등록
    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public ResponseEntity<Long> savePost(@RequestBody PostDto postDto) throws Exception {
        if (postDto.getContent() == null) {
            postDto.setContent("");
        }
        postDto.setCreatedAt(LocalDateTime.now());
        postDto.setUpdatedAt(LocalDateTime.now());
        Long postId = postService.savePost(postDto);
        return new ResponseEntity<>(postId, HttpStatus.CREATED);
    }

    //게시글 상세보기
    @GetMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) throws Exception {
        PostDto postDTO = postService.getPost(postId);
        // increase view count
        postService.increaseViewCount(postId);
        return new ResponseEntity<PostDto>(postDTO, HttpStatus.OK);
    }

    //글 수정하기
    @PutMapping(value = "/posts/edit/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable() Long postId) throws Exception {
        postDto.setUpdatedAt(LocalDateTime.now());
        PostDto updatePost =  postService.updatePost(postId, postDto);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //글 삭제하기
    @DeleteMapping(value = "/posts/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) throws Exception {
        postService.deletePost(postId);
        return "redirect:/board";
    }

    //검색하기
    @GetMapping(value = "/board/search")
    @ResponseBody
    public Page<BoardDto> searchPost(@RequestParam(value = "keyword")String keyword, @RequestParam(value = "target") String target, Pageable pageable) throws Exception{
        return postService.searchBoard(keyword, target, pageable);
    }

}

