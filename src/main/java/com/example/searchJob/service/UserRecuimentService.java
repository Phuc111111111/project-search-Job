package com.example.searchJob.service;



import java.util.List;

import com.example.searchJob.entity.UserRecuitment;

public interface UserRecuimentService {
    UserRecuitment findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId);

    void save(UserRecuitment userRecuitment);

    void delete(UserRecuitment userRecuitment);

    List<UserRecuitment> findByUserId(Integer id);

    void deleteById(Integer userRecuitment);
    
    
}
