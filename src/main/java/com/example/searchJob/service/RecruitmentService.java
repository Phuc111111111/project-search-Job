package com.example.searchJob.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.searchJob.entity.Recruitment;

import java.util.List;

public interface RecruitmentService {
    List<Integer> getTop5CompanyId();
    
    List<Recruitment> findAll();

    Recruitment findById(Integer id);

    void save(Recruitment recruitment);

    void deleteById(Integer recruitmentId);

    Page<Recruitment> findByTitleLikeAndPageable(String keySearch, Pageable pageable);

    Page<Recruitment> findByAddressLikeAndPageable(String keySearch, Pageable pageable);

    Page<Recruitment> findByCompanyIdListAndPageable(List<Integer> companyIdList, Pageable pageable);

    Page<Recruitment> findByCompanyIdAndPageable(Integer id, Pageable pageable);
    
    Page<Recruitment> findByUserIdAndPageable(Integer id, Pageable pageable);
    
    
}
