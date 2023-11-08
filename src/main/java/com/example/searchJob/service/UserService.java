package com.example.asm2.service;

import com.example.asm2.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    boolean userExists(String email);

    void saveUser(User user);

    void sendVerificationEmail(User user, String appUrl) throws MessagingException, UnsupportedEncodingException;

    boolean verify(String code);

}
