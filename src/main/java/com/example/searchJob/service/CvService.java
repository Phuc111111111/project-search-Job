package com.example.asm2.service;

import com.example.asm2.entity.Cv;
import com.example.asm2.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface CvService {
    void save(Cv cv);

    void deleteById(Integer cvId);

    Cv findById(Integer cvId);

    Cv createCvForUser(MultipartFile file, User user);
}
