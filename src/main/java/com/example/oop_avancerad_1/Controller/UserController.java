package com.example.oop_avancerad_1.Controller;

import com.example.oop_avancerad_1.Entity.User;
import com.example.oop_avancerad_1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import javax.xml.bind.DatatypeConverter;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(@ModelAttribute("user") User user){
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model
    ){
        User user = userService.getUserByUsername(username);

        if(user != null && userService.authUser(username, password)){
            System.out.println("inside login success");
            return "redirect:/feed";
        }
        return "redirect:/fail";
    }


    @PostMapping("/saveuser")
    public String saveUser(User user,
                           @RequestParam("password") String password,
                           @RequestParam("passwordTwo") String passwordTwo) {
        if(!password.equals(passwordTwo)){
            return "redirect:/fail";
        }
        userService.saveUser(user);
        return "feed";
    }

    @GetMapping("/success")
    public String success(@ModelAttribute("user") User user,
                          Model model){
        model.addAttribute("msg", "You are registered!");
        return "index";
    }

    @GetMapping("/fail")
    public String failed(@ModelAttribute("user") User user,
                         Model model){
        model.addAttribute("msg", "Something went wrong.");
        return "index";
    }
}
