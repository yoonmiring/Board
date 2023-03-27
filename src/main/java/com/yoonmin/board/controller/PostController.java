//package com.yoonmin.board.controller;
//
//
//import com.yoonmin.board.domain.dto.PostDto;
//import com.yoonmin.board.service.PostService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//@RestController
//@RequestMapping(value="/")
//public class PostController {
//    @Autowired
//    private PostService postService;
////    //글쓰기
////    @RequestMapping(value = "/board/posts", method = RequestMethod.POST)
////    public String insertBoard(PostDto postDto) throws Exception{
////        return "redirect:/board";
////    }
//
//////    글 상세보기
////    @RequestMapping(value = "/board/posts/{boardId}", method=RequestMethod.GET)
////    public ModelAndView openBoardDetail(@PathVariable("boardId") Long boardId) throws Exception {
////        ModelAndView mv = new ModelAndView("/board/detail");
////        PostDto postDTO = postService.getPost(boardId);
////
////        mv.addObject("PostDto", postDTO);
////        return mv;
////    }
//
//////    글 수정하기
////
////    @RequestMapping(value ="/board/posts/{boardId}", method=RequestMethod.PUT)
////    public String savePost(PostDto postDTO) throws Exception {
////        postService.savePost(postDTO);
////        return "redirect:/board";
////    }
////    글 삭제하기
////    @RequestMapping(value ="/board/posts/{boardId}", method=RequestMethod.DELETE)
////    public String deleteBoard(@PathVariable("boardId") Long boardId) throws Exception{
////        postService.deletePost(boardId);
////        return "redirect:/board";
////    }
//
//
//
//
//
//}
