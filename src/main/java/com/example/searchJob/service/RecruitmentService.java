package com.example.asm2.service;

import com.example.asm2.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitmentService {
    List<Integer> getTop5CompanyId();

    Recruitment findById(Integer id);

    void save(Recruitment recruitment);

    void deleteById(Integer recruitmentId);

    Page<Recruitment> findByTitleLikeAndPageable(String keySearch, Pageable pageable);

    Page<Recruitment> findByAddressLikeAndPageable(String keySearch, Pageable pageable);

    Page<Recruitment> findByCompanyIdListAndPageable(List<Integer> companyIdList, Pageable pageable);

    Page<Recruitment> findByCompanyIdAndPageable(Integer id, Pageable pageable);
}
