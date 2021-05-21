package com.example.oop_avancerad_1.Service;

import com.example.oop_avancerad_1.Entity.User;
import com.example.oop_avancerad_1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){

        byte[] salt = generateSalt();
        String saltString = convertByteToStringForDb(salt);
        String hashedPass = createSecureHashPass(user.getPassword(), salt);
        if(!hashedPass.equals("")){
            user.setSalt(saltString);
            user.setPassword(hashedPass);
            userRepository.save(user);
        }
    }
    public boolean authUser(String username, String plainTextPass){
        User user = userRepository.findByUsername(username);
        if(user == null) { return false; }
        String dbSalt = user.getSalt();
        String comparePass = createSecureHashPass(plainTextPass, convertStringToByteForDb(dbSalt));
        return user.getPassword().equals(comparePass);
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
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
}
