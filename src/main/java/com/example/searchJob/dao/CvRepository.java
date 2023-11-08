package com.example.asm2.dao;

import com.example.asm2.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends JpaRepository<Cv, Integer> {
}
