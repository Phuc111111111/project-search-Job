package com.example.searchJob.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.searchJob.entity.UserRecuitment;

import java.util.List;

@Repository
public interface UserRecuitmentRepository extends JpaRepository<UserRecuitment, Integer> {
    @Query("select ur from UserRecuitment ur where ur.recruitment.id = ?1 and ur.user.id = ?2")
    UserRecuitment findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId);

    @Query("select ur from UserRecuitment ur where ur.user.id = ?1")
    List<UserRecuitment> findByUserId(Integer userId);
}
