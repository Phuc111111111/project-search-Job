package com.example.searchJob.service;



import java.util.List;

import com.example.searchJob.entity.FollowCompany;

public interface FollowCompanyService {
    FollowCompany findByCompanyIdAndUserId(Integer companyId, Integer userId);

    void save(FollowCompany followCompany);

    void delete(FollowCompany followCompany);

    List<FollowCompany> findByUserId(Integer id);

    void deleteById(Integer followCompanyId);
}
