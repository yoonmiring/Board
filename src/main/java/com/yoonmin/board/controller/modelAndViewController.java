package com.yoonmin.board.controller;

import com.yoonmin.board.domain.dto.BoardDto;
import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.domain.repository.PostRepository;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;




@RestController
@RequestMapping(value = "/")
public class modelAndViewController {
    //화면처리
    @Autowired
    private PostService postService;

//    홈 화면, 전체목록보기(데이터)
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    @ResponseBody()
    public ModelAndView list(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "15") int size) throws Exception {
        Pageable pageable = PageRequest.of(page , size);
        Page<BoardDto> postList = postService.getPostlist(pageable);
        ModelAndView mv = new ModelAndView("/board/home");
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
    @RequestMapping(value = "/board/search", method = RequestMethod.GET)
    public ModelAndView searchKeyword(@RequestParam(value = "keyword")  String keyword,
                                      @RequestParam(value = "target") String target,
                                      @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 15, Sort.Direction.DESC, "id");
        Page<BoardDto> postList = postService.searchBoard(keyword, target, pageable);
        ModelAndView mv = new ModelAndView("/board/home");
//        Page<BoardDto> postSearchPage = postService.searchBoard(keyword, target, pageable);
        mv.addObject("postList", postList);
        return mv;
    }
//    //검색 화면
//    @RequestMapping(value = "/board/search", method = RequestMethod.GET)
//    public ModelAndView searchKeyword(@RequestParam(value = "keyword")  String keyword, @RequestParam(value = "target") String target) throws Exception {
//        ModelAndView mv = new ModelAndView("/board/home");
//        List<BoardDto> postSearchList = postService.searchBoard(keyword,target);
//        mv.addObject("postList", postSearchList);
//        return mv;
//    }
}
