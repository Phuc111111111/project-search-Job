package com.example.searchJob.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dao.ApplyPostRepository;
import com.example.searchJob.entity.ApplyPost;
import com.example.searchJob.entity.Recruitment;
import com.example.searchJob.entity.User;

import java.util.List;

@Service
public class ApplyPostServiceImpl implements ApplyPostService {
    private ApplyPostRepository applyPostRepository;

    @Autowired
    public ApplyPostServiceImpl(ApplyPostRepository applyPostRepository) {
        this.applyPostRepository = applyPostRepository;
    }

    @Override
    @Transactional
    public List<Integer> getTop5RecruitmentId() {
        return applyPostRepository.getTop5RecruitmentId();
    }

    @Override
    @Transactional
    public List<ApplyPost> findByRecruitmentId(Integer recruitmentId) {
        return applyPostRepository.findByRecruitmentId(recruitmentId);
    }

    @Override
    @Transactional
    public void save(ApplyPost applyPost) {
        applyPostRepository.save(applyPost);
    }

    @Override
    @Transactional
    public ApplyPost findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId) {
        return applyPostRepository.findByRecruitmentIdAndUserId(recruitmentId, userId);
    }

    @Override
    @Transactional
    public List<ApplyPost> findByUserId(Integer id) {
        return applyPostRepository.findByUserId(id);
    }

    @Override
    @Transactional
    public void deleteById(Integer applyPostId) {
        applyPostRepository.deleteById(applyPostId);
    }

    @Override
    public ApplyPost createApplyPost(User user, Recruitment recruitment, String text) {
        ApplyPost applyPost = new ApplyPost();
        applyPost.setCreatedAt(java.time.LocalDate.now().toString());
        applyPost.setRecruitment(recruitment);
        applyPost.setUser(user);
        applyPost.setText(text.trim());

        return applyPost;
    }
}
