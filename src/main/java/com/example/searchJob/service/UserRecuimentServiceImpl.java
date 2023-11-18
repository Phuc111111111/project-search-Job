package com.example.searchJob.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dao.UserRecuitmentRepository;
import com.example.searchJob.entity.UserRecuitment;

import java.util.List;

@Service
public class UserRecuimentServiceImpl implements UserRecuimentService{
    private UserRecuitmentRepository userRecuitmentRepository;

    @Autowired
    public UserRecuimentServiceImpl(UserRecuitmentRepository userRecuitmentRepository) {
        this.userRecuitmentRepository = userRecuitmentRepository;
    }

    @Override
    @Transactional
    public UserRecuitment findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId) {
        return userRecuitmentRepository.findByRecruitmentIdAndUserId(recruitmentId, userId);
    }

    @Override
    @Transactional
    public void save(UserRecuitment userRecuitmet) {
    	userRecuitmentRepository.save(userRecuitmet);
    }

    @Override
    @Transactional
    public void delete(UserRecuitment userRecuitmet) {
    	userRecuitmentRepository.delete(userRecuitmet);
    }

    @Override
    @Transactional
    public List<UserRecuitment> findByUserId(Integer id) {
        return userRecuitmentRepository.findByUserId(id);
    }

    @Override
    @Transactional
    public void deleteById(Integer userRecuitmet) {
    	userRecuitmentRepository.deleteById(userRecuitmet);
    }
}
