package com.example.asm2.dao;

import com.example.asm2.entity.SaveJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaveJobRepository extends JpaRepository<SaveJob, Integer> {
    @Query("select s from SaveJob s where s.recruitment.id = ?1 and s.user.id = ?2")
    SaveJob findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId);

    @Query("select s from SaveJob s where s.user.id = ?1")
    List<SaveJob> findByUserId(Integer userId);
}
