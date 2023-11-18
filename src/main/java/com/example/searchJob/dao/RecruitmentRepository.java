package com.example.searchJob.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.searchJob.entity.Recruitment;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    @Query(value = "SELECT company_id FROM recruitment GROUP BY company_id ORDER BY count(*) desc", nativeQuery = true)
    List<Integer> getTop5CompanyId();

    @Query("select r from Recruitment r where r.title like %?1%")
    Page<Recruitment> findByTitleLikeAndPageable(String keySearch, Pageable pageable);

    @Query("select r from Recruitment r where r.address like %?1%")
    Page<Recruitment> findByAddressLikeAndPageable(String keySearch, Pageable pageable);

    @Query("select r from Recruitment r where r.company.id in ?1")
    Page<Recruitment> findByCompanyIdListAndPageable(List<Integer> companyIdList, Pageable pageable);

    @Query("select r from Recruitment r where r.company.id = ?1")
    Page<Recruitment> findByCompanyIdAndPageable(Integer companyId, Pageable pageable);
    
    @Query(value = "SELECT r.* "
    		+ "FROM users u "
    		+ "JOIN user_recruitment ur ON u.id = ur.user_id "
    		+ "JOIN recruitment r ON ur.recruitment_id = r.id "
    		+ "WHERE u.id = ?1", nativeQuery = true)
    Page<Recruitment> findRecruitmentByUserIdAndPageable(Integer userId, Pageable pageable);
    
    
}
