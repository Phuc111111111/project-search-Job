package com.example.searchJob.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.searchJob.dao.RecruitmentRepository;
import com.example.searchJob.entity.Recruitment;

import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    public RecruitmentServiceImpl(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    @Transactional
    public List<Integer> getTop5CompanyId() {
        return recruitmentRepository.getTop5CompanyId();
    }

    @Override
    @Transactional
    public Recruitment findById(Integer id) {
        Optional<Recruitment> result = recruitmentRepository.findById(id);
        Recruitment recruitment = null;

        if (result.isPresent()) {
            recruitment = result.get();
        } else {
            throw new RuntimeException("Did not find company id - " + id);
        }
        return recruitment;
    }

    @Override
    @Transactional
    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    @Override
    @Transactional
    public void deleteById(Integer recruitmentId) {
        recruitmentRepository.deleteById(recruitmentId);
    }

    @Override
    @Transactional
    public Page<Recruitment> findByTitleLikeAndPageable(String keySearch, Pageable pageable) {
        return recruitmentRepository.findByTitleLikeAndPageable(keySearch, pageable);
    }

    @Override
    @Transactional
    public Page<Recruitment> findByAddressLikeAndPageable(String keySearch, Pageable pageable) {
        return recruitmentRepository.findByAddressLikeAndPageable(keySearch, pageable);
    }

    @Override
    @Transactional
    public Page<Recruitment> findByCompanyIdListAndPageable(List<Integer> companyIdList, Pageable pageable) {
        return recruitmentRepository.findByCompanyIdListAndPageable(companyIdList, pageable);
    }

    @Override
    @Transactional
    public Page<Recruitment> findByCompanyIdAndPageable(Integer companyId, Pageable pageable) {
        return recruitmentRepository.findByCompanyIdAndPageable(companyId, pageable);
    }

	@Override
	public Page<Recruitment> findByUserIdAndPageable(Integer id, Pageable pageable) {
		// TODO Auto-generated method stub
		return recruitmentRepository.findRecruitmentByUserIdAndPageable(id, pageable);
	}

	@Override
	public List<Recruitment> findAll() {
		// TODO Auto-generated method stub
		return recruitmentRepository.findAll();
	}
    
	
}
