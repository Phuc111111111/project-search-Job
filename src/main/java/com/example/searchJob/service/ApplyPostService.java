package com.example.searchJob.service;


import org.springframework.web.multipart.MultipartFile;

import com.example.searchJob.entity.ApplyPost;
import com.example.searchJob.entity.Recruitment;
import com.example.searchJob.entity.User;

import java.util.List;

public interface ApplyPostService {
    List<Integer> getTop5RecruitmentId();

    List<ApplyPost> findByRecruitmentId(Integer recruitmentId);

    void save(ApplyPost applyPost);

    ApplyPost findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId);

    List<ApplyPost> findByUserId(Integer id);

    void deleteById(Integer applyPostId);

    ApplyPost createApplyPost(User user, Recruitment recruitment, String text);
}
