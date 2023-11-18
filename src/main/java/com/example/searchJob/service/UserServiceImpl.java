package com.example.searchJob.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dao.UserRepository;
import com.example.searchJob.entity.User;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private JavaMailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository ,JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }
    



	@Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public boolean userExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String fromAddress = "19102000ph@gmail.com";
        String senderName = "Work CV";
        String toAddress = user.getEmail();
        String subject = "Please verify your registration";
        String verifyUrl = siteURL + "/verify?code=" + user.getVerificationCode();
        String content = "Dear " + user.getFullName() + ",<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\""+ verifyUrl + "\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Work CV";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @Transactional
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("Account has not been verified!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRoleName()))); // param number 3 must be a Collection
    }
}
