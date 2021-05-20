package com.example.oop_avancerad_1.Controller;

import com.example.oop_avancerad_1.Entity.User;
import com.example.oop_avancerad_1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/saveuser")
    public String saveUser() {
        User user = new User("username", "firstName", "lastName", "password", "salt", "img");

        byte[] salt = generateSalt();
        String saltString = convertByteToStringForDb(salt);
        String hashedPass = createSecureHashPass(user.getPassword(), salt);
        if(!hashedPass.equals("")){
            user.setSalt(saltString);
            user.setPassword(hashedPass);
            userService.saveUser(user);
        }
        return "redirect:/";
    }

    private String createSecureHashPass(String password, byte[] salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPass = md.digest(password.getBytes());
            return convertByteToStringForDb(hashedPass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private byte[] convertStringToByteForDb(String stringToByte) {
        return DatatypeConverter.parseHexBinary(stringToByte);
    }

    private String convertByteToStringForDb(byte[] byteToString) {
        return DatatypeConverter.printHexBinary(byteToString).toLowerCase();
    }

    private byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = sr.generateSeed(12);
        return salt;
    }

}
