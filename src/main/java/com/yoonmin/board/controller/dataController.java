package com.yoonmin.board.controller;

import com.yoonmin.board.domain.dto.BoardDto;
import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.domain.repository.PostRepository;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.yoonmin.board.domain.dto.PostDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
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

    //게시글 상세보기
    @GetMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) throws Exception {
        PostDto postDTO = postService.getPost(postId);
        return new ResponseEntity<PostDto>(postDTO, HttpStatus.OK);
    }

    //글 수정하기
    @PutMapping(value = "/posts/edit/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDTO, @PathVariable() Long postId) throws Exception {
        PostDto updatePost =  postService.updatePost(postId, postDTO);
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
    public List<BoardDto> searchPost(@RequestParam(value = "keyword")String keyword, @RequestParam(value = "target") String target) throws Exception{
        return postService.searchBoard(keyword,target);
    }
}

