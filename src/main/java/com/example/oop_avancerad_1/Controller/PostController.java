package com.example.oop_avancerad_1.Controller;

import com.example.oop_avancerad_1.Entity.Post;
import com.example.oop_avancerad_1.Entity.User;
import com.example.oop_avancerad_1.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/feed")
    public String feed(@ModelAttribute("post") Post post, Model model){
        model.addAttribute("posts", postService.getAllPosts());
        return "feed";
    }

    @PostMapping("/savepost")
    public String savePost(@ModelAttribute("post") Post post) {
        System.out.println(post.getText());

        postService.savePost(post);
        return "redirect:/feed";
    }
}
