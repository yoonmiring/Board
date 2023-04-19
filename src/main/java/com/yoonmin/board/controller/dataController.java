package com.yoonmin.board.controller;


import com.yoonmin.board.domain.dto.BoardDto;
import com.yoonmin.board.domain.dto.CommentDto;
import com.yoonmin.board.domain.repository.PostRepository;
import com.yoonmin.board.service.CommentService;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.yoonmin.board.domain.dto.PostDto;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value="/api")
public class dataController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentService commentService;


    //main Board data
    @GetMapping(value = "/board", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BoardDto>> list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int size) throws Exception {
        Pageable pageable = PageRequest.of(page , size);
        Page<BoardDto> postList = postService.getPostlist(pageable);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
    //작성글 등록
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
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
        PostDto postDto = postService.getPost(postId);
        // increase view count
        postService.increaseViewCount(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    //글 수정하기
    @PutMapping(value = "/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Long postId) throws Exception {
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
    @RequestMapping(value = "/board/search", method = RequestMethod.GET)
    public Page<BoardDto> searchPost(@RequestParam(value = "keyword")  String keyword,
                                     @RequestParam(value = "target") String target,
                                     @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC, value = 1) Pageable pageable) throws Exception {
        return postService.searchBoard(keyword, target, pageable);
    }
    //댓글조회
    @GetMapping(value = "/{postId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> list(@PathVariable Long postId) throws Exception {
        List<CommentDto> commentList= commentService.getCommentListByPostId(postId);

        return new ResponseEntity<List<CommentDto>>(commentList, HttpStatus.OK);
    }
    //댓글 등록
    @RequestMapping(value = "/{postId}/comment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> saveComment (@PathVariable Long postId,@RequestBody CommentDto commentDto) throws Exception {
        if (commentDto.getContent() == null) {
            commentDto.setContent("");
        }
        commentDto.setCreatedAt(LocalDateTime.now());
        commentDto.setPostId(postId);
        Long commentId = commentService.saveComment(commentDto);
        return new ResponseEntity<>(commentId, HttpStatus.CREATED);
    }
    //댓글 삭제
    @DeleteMapping(value = "/{postId}/comment/{commentId}")
    public String deleteComment (@PathVariable("commentId") Long commentId) throws Exception {
        commentService.deleteComment(commentId);
        return "redirect:/posts/{postId}";
    }
    //    비밀번호 일치 여부
    @PostMapping("/posts/edit/{postId}/password")
    @ResponseBody
    public boolean checkPassword(@PathVariable Long postId,  @RequestBody Map<String, Object> requestData) {
        String password = (String) requestData.get("password");
        // 게시물 ID로 게시물 정보 조회
        PostDto postDto = postService.getPost(postId);
        // 입력받은 비밀번호와 저장된 해시값 비교
        return postService.checkPassword(postDto, password);
    }
}

