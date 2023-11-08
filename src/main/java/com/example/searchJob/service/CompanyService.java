package com.example.asm2.service;

import com.example.asm2.entity.Company;

import java.util.List;

public interface CompanyService {
    Company findById(Integer id);

    void save(Company company);

    List<Integer> findByNameCompanyLike(String keySearch);

}
