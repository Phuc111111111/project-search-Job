package com.example.asm2.dao;

import com.example.asm2.entity.ApplyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyPostRepository extends JpaRepository<ApplyPost, Integer> {
    @Query(value = "SELECT recruitment_id FROM applypost GROUP BY recruitment_id ORDER BY count(*) desc LIMIT 5", nativeQuery = true)
    List<Integer> getTop5RecruitmentId();

    @Query("select a from ApplyPost a where a.recruitment.id = ?1")
    List<ApplyPost> findByRecruitmentId(Integer recruitmentId);

    @Query("select a from ApplyPost a where a.recruitment.id = ?1 and a.user.id = ?2")
    ApplyPost findByRecruitmentIdAndUserId(Integer recruitmentId, Integer userId);

    @Query("select a from ApplyPost a where a.user.id = ?1")
    List<ApplyPost> findByUserId(Integer userId);
}
