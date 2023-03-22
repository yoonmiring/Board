package com.yoonmin.board.controller;

import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="/")
public class pageController {
//화면처리
    @Autowired
    private PostService postService;

    // 홈 화면, 전체목록보기(데이터)
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView list() throws Exception{
        ModelAndView mv = new ModelAndView("/board/home");
        List<PostDto> postList = postService.getPostlist();
        mv.addObject("postList", postList);
        return mv;
    }

    //글쓰기 화면
    @RequestMapping(value ="/board/posts", method = RequestMethod.GET)
    public String openBoardWrite() throws Exception{
        return "/board/post";
    }
    @RequestMapping(value = "/board/posts", method = RequestMethod.POST)
    public String insertBoard(PostDto postDto) throws Exception{
        return "redirect:/board";
    }
    //글 상세 화면


    //검색 화면
    @RequestMapping(value ="/board/posts/{keyword}", method=RequestMethod.POST)
    public ModelAndView searchKeyword (@RequestParam(value="keyword") String keyword) throws Exception{
        ModelAndView mv = new ModelAndView("/board/home");
        List<PostDto> postDtoList = postService.searchPosts(keyword);
        mv.addObject("postList",postDtoList);
        return mv;
    }
}
