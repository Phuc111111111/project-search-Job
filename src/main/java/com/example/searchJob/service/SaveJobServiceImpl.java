package com.example.asm2.service;

import com.example.asm2.dao.SaveJobRepository;
import com.example.asm2.entity.SaveJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaveJobServiceImpl implements SaveJobService{
    private SaveJobRepository saveJobRepository;

    @Autowired
    public SaveJobServiceImpl(SaveJobRepository saveJobRepository) {
        this.saveJobRepository = saveJobRepository;
    }

    @Override
    @Transactional
    public SaveJob findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId) {
        return saveJobRepository.findByRecruitmentIdAndUserId(recruitmentId, userId);
    }

    @Override
    @Transactional
    public void save(SaveJob saveJob) {
        saveJobRepository.save(saveJob);
    }

    @Override
    @Transactional
    public void delete(SaveJob saveJob) {
        saveJobRepository.delete(saveJob);
    }

    @Override
    @Transactional
    public List<SaveJob> findByUserId(Integer id) {
        return saveJobRepository.findByUserId(id);
    }

    @Override
    @Transactional
    public void deleteById(Integer saveJobId) {
        saveJobRepository.deleteById(saveJobId);
    }
}
