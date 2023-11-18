package com.example.searchJob.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dao.FollowCompanyRepository;
import com.example.searchJob.entity.FollowCompany;

import java.util.List;

@Service
public class FollowCompanyServiceImpl implements FollowCompanyService{
    private FollowCompanyRepository followCompanyRepository;

    @Autowired
    public FollowCompanyServiceImpl(FollowCompanyRepository followCompanyRepository) {
        this.followCompanyRepository = followCompanyRepository;
    }

    @Override
    @Transactional
    public FollowCompany findByCompanyIdAndUserId(Integer companyId, Integer userId) {
        return followCompanyRepository.findByCompanyIdAndUserId(companyId, userId);
    }

    @Override
    @Transactional
    public void save(FollowCompany followCompany) {
        followCompanyRepository.save(followCompany);
    }

    @Override
    @Transactional
    public void delete(FollowCompany followCompany) {
        followCompanyRepository.delete(followCompany);
    }

    @Override
    @Transactional
    public List<FollowCompany> findByUserId(Integer id) {
        return followCompanyRepository.findByUserId(id);
    }

    @Override
    @Transactional
    public void deleteById(Integer followCompanyId) {
        followCompanyRepository.deleteById(followCompanyId);
    }
}
