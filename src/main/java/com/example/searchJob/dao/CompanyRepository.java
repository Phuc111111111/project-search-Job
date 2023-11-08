package com.example.asm2.dao;

import com.example.asm2.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("select c.id from Company c where c.nameCompany like %?1%")
    List<Integer> findByNameCompanyLike(String keySearch);

}
