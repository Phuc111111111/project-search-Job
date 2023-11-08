package com.example.asm2.service;

import com.example.asm2.entity.FollowCompany;

import java.util.List;

public interface FollowCompanyService {
    FollowCompany findByCompanyIdAndUserId(Integer companyId, Integer userId);

    void save(FollowCompany followCompany);

    void delete(FollowCompany followCompany);

    List<FollowCompany> findByUserId(Integer id);

    void deleteById(Integer followCompanyId);
}
