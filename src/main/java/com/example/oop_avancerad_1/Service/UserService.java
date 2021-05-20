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
        userRepository.save(user);
    }
}
