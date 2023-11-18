package com.example.searchJob.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.searchJob.entity.Cv;

@Repository
public interface CvRepository extends JpaRepository<Cv, Integer> {
}
