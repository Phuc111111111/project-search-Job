package com.example.asm2.dao;

import com.example.asm2.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT * FROM category ORDER BY number_choose desc LIMIT 4", nativeQuery = true)
    List<Category> getTop4Category();

}
