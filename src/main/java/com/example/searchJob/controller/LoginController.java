package com.example.searchJob.controller;


import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.searchJob.entity.User;
import com.example.searchJob.service.UserService;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Controller
public class LoginController {
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(Model theModel) {
        User user = (User) theModel.getAttribute("user");
        if (user != null) {
            theModel.addAttribute("user", user);
        } else {
            theModel.addAttribute("user", new User());
        }
        return "public/login";
    }

    @PostMapping("/register")
    public String register(RedirectAttributes theModel,
                           @ModelAttribute("user") User user,
                           @RequestParam("rePassword") String rePassword,
                           HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

        // check password
        if (!user.getPassword().equals(rePassword)) {
            theModel.addFlashAttribute("msg_register_error","msg_register_error");
            theModel.addFlashAttribute("user", user);
            return "redirect:/showLoginPage";
        }

        // check the database if user already exists
        if (userService.userExists(user.getEmail())) {
            theModel.addFlashAttribute("email_exists","email_exists");
            theModel.addFlashAttribute("user", user);
            return "redirect:/showLoginPage";
        }

        // encrypt password from plaintext to Bcrypt and save to database
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        String randomCode = UUID.randomUUID().toString();
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        userService.saveUser(user);

        String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.sendVerificationEmail(user, siteURL);

        return "public/register-success";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code) {
        if (userService.verify(code)) {
            return "public/verify-success";
        } else {
            return "public/verify-fail";
        }
    	
    }
}
