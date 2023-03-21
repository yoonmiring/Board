package com.yoonmin.board.controller;


import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
@RequestMapping(value="/")
public class PostController {
    @Autowired
    private PostService postService;

//  목록보기
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView list() throws Exception{
        ModelAndView mv = new ModelAndView("/board/home");
        List<PostDto> postList = postService.getPostlist();
        mv.addObject("postList", postList);
        return mv;
    }

//  글쓰기창 열기
    @RequestMapping(value = "/board/posts", method = RequestMethod.GET)
    public String openBoardWrite() throws Exception{
        return "/board/post";
    }
    //글쓰기
    @RequestMapping(value = "/board/posts", method = RequestMethod.POST)
    public String insertBoard(PostDto postDto) throws Exception{
        return "redirect:/board";
    }

//    글 상세보기
    @RequestMapping(value = "/board/posts/{boardId}", method=RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardId") Long boardId) throws Exception {
        ModelAndView mv = new ModelAndView("detail");
        PostDto postDTO = postService.getPost(boardId);

        mv.addObject("PostDto", postDTO);
        return mv;
    }

//    글 수정하기

    @RequestMapping(value ="/board/posts/{boardId}", method=RequestMethod.PUT)
    public String savePost(PostDto postDTO) throws Exception {
        postService.savePost(postDTO);
        return "redirect:/board";
    }
//    글 삭제하기
    @RequestMapping(value ="/board/posts/{boardId}", method=RequestMethod.DELETE)
    public String deleteBoard(@PathVariable("boardId") Long boardId) throws Exception{
        postService.deletePost(boardId);
        return "redirect:/board";
    }


////    검색
//    @GetMapping("/post/search")
//    public String search (@RequestParam(value="keyword") String keyword, Model model){
//        List<PostDto> postDtoList = postService.searchPosts(keyword);
//        model.addAttribute("postList",postDtoList);
//        return "board/home.html";
//    }


}
