package com.example.asm2.service;

import com.example.asm2.entity.ApplyPost;
import com.example.asm2.entity.Recruitment;
import com.example.asm2.entity.User;
import org.springframework.web.multipart.MultipartFile;

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
