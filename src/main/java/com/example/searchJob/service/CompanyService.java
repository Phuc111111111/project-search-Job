package com.example.searchJob.service;



import java.util.List;

import com.example.searchJob.entity.Company;

public interface CompanyService {
    Company findById(Integer id);
    
    List<Company> findAll();

    void save(Company company);

    List<Integer> findByNameCompanyLike(String keySearch);

}
