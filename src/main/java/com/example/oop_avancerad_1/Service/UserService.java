package com.example.oop_avancerad_1.Service;

import com.example.oop_avancerad_1.Entity.User;
import com.example.oop_avancerad_1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        String encryptedPassword = encryptPassword(user.getPassword(), 3);
        user.setPassword(encryptedPassword);
        userRepository.save(user);

    }

    private String encryptPassword(String plainText, int encryptionKey) {
        StringBuilder sb = new StringBuilder();
        char [] chars = plainText.toCharArray();
        for(char c: chars){
            c = (char)(c+ encryptionKey);
            sb.append(c);
        }
        return sb.toString();
    }

}
