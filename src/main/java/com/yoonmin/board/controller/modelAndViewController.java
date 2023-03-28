package com.yoonmin.board.controller;

import com.yoonmin.board.domain.dto.BoardDto;
import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping(value = "/")
public class modelAndViewController {
    //화면처리
    @Autowired
    private PostService postService;

    //홈 화면, 전체목록보기(데이터)
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    @ResponseBody()
    public ModelAndView list() throws Exception {
        ModelAndView mv = new ModelAndView("/board/home");
        List<BoardDto> postList = postService.getPostlist();
        mv.addObject("postList", postList);
        return mv;
    }

    //글 상세 화면
    @RequestMapping(value = "/posts/{post_id}", method = RequestMethod.GET)
    @ResponseBody()
    public ModelAndView openBoardDetail(@PathVariable("post_id") Long boardId) throws Exception {
        ModelAndView mv = new ModelAndView("board/detail");
        PostDto postDTO = postService.getPost(boardId);
        mv.addObject("PostDto", postDTO);
        return mv;
    }

    //글 수정 화면
    @RequestMapping(value = "/posts/edit/{post_id}", method = RequestMethod.GET)
    @ResponseBody()
    public ModelAndView openBoardUpdate(@PathVariable("post_id") Long boardId) throws Exception {
        ModelAndView mv = new ModelAndView("/board/update");
        PostDto postDTO = postService.getPost(boardId);
        mv.addObject("PostDto", postDTO);
        return mv;
    }


    //검색 화면
    @RequestMapping(value = "/board/posts/{keyword}", method = RequestMethod.GET)
    public ModelAndView searchKeyword(@RequestParam(value = "keyword") String keyword) throws Exception {
        ModelAndView mv = new ModelAndView("/board/home");
        List<BoardDto> postDtoList = postService.searchPosts(keyword);
        mv.addObject("postList", postDtoList);
        return mv;
    }

}
