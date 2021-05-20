package com.example.oop_avancerad_1.Controller;

import com.example.oop_avancerad_1.Entity.User;
import com.example.oop_avancerad_1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/saveuser")
    public String saveUser() {
        System.out.println("inside post handling");
        User user = new User("username", "firstName", "lastName", "password", "salt", "img");
        userService.saveUser(user);
        return "redirect:/";
    }

}
