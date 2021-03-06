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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
                        Model model,
                        HttpServletResponse response
    ){
        User user = userService.getUserByUsername(username);

        if(user != null && userService.authUser(username, password)){
            Cookie cookie = new Cookie("currentUserId", user.getIdString());
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return "redirect:/feed";
        }
        return "redirect:/fail";
    }


    @PostMapping("/saveuser")
    public String saveUser(User user,
                           @RequestParam("password") String password,
                           @RequestParam("passwordTwo") String passwordTwo,
                           HttpServletResponse response) {
        if(!password.equals(passwordTwo)){
            return "redirect:/fail";
        }
        userService.saveUser(user);
        Cookie cookie = new Cookie("currentUserId", user.getIdString());
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
        return "redirect:/feed";
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

    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("currentUserId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
