package com.example.searchJob.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.searchJob.entity.FollowCompany;

import java.util.List;

@Repository
public interface FollowCompanyRepository extends JpaRepository<FollowCompany, Integer> {

    @Query("select f from FollowCompany f where f.company.id = ?1 and f.user.id = ?2")
    FollowCompany findByCompanyIdAndUserId(Integer CompanyId, Integer userId);

    @Query("select f from FollowCompany f where f.user.id = ?1")
    List<FollowCompany> findByUserId(Integer userId);
}
