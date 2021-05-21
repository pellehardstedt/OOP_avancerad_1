package com.example.oop_avancerad_1.Controller;

import com.example.oop_avancerad_1.Entity.Post;
import com.example.oop_avancerad_1.Entity.User;
import com.example.oop_avancerad_1.Service.PostService;
import com.example.oop_avancerad_1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/feed")
    public String feed(@ModelAttribute("post") Post post,
                       @CookieValue("currentUserId") String currentUserId,
                       Model model){
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("user", userService.getUserById(Long.parseLong(currentUserId)));
        return "feed";
    }


    @PostMapping("/savepost")
    public String savePost(@ModelAttribute("post") Post post,
                           @CookieValue("currentUserId") String currentUserId) {
        User user = userService.getUserById(Long.parseLong(currentUserId));
        post.setUser(user);

        postService.savePost(post);
        return "redirect:/feed";
    }
}
