package com.yoonmin.board.controller;

import com.yoonmin.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class pageController {
    @Autowired
    private PostService postService;

    //글쓰기 화면
    @GetMapping("/board/posts")
    public String openBoardWrite() {
        return "board/post";
    }
}
