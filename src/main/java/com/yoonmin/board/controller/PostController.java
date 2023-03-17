package com.yoonmin.board.controller;

import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PostController {
    private PostService postService;


    @GetMapping("/")
    public String list(Model model){

        List<PostDto> postList = postService.getPostlist();

        model.addAttribute("postList", postList);
        return "board/home.html";
    }


    @GetMapping("/post")
    public String write(){
        return "board/write.html";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        PostDto postDTO = postService.getPost(no);

        model.addAttribute("PostDto", postDTO);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        PostDto postDTO = postService.getPost(no);

        model.addAttribute("PostDto", postDTO);
        return "board/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(PostDto postDTO) {
        postService.savePost(postDTO);

        return "redirect:/";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        postService.deletePost(no);

        return "redirect:/";
    }

    @PostMapping ("/post")
    public String write(PostDto postDto){
       postService.savePost(postDto);

       return "redirect:/";
    }
}
