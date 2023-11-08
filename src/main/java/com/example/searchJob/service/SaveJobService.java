package com.example.asm2.service;

import com.example.asm2.entity.SaveJob;

import java.util.List;

public interface SaveJobService {
    SaveJob findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId);

    void save(SaveJob saveJob);

    void delete(SaveJob saveJob);

    List<SaveJob> findByUserId(Integer id);

    void deleteById(Integer saveJobId);
}
