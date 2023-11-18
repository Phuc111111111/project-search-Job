package com.example.searchJob.service;


import org.springframework.web.multipart.MultipartFile;

import com.example.searchJob.entity.Cv;
import com.example.searchJob.entity.User;

public interface CvService {
    void save(Cv cv);

    void deleteById(Integer cvId);

    Cv findById(Integer cvId);

    Cv createCvForUser(MultipartFile file, User user);
}
