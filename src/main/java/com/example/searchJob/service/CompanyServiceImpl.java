package com.example.asm2.service;

import com.example.asm2.dao.CompanyRepository;
import com.example.asm2.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public Company findById(Integer id) {
        Optional<Company> result = companyRepository.findById(id);
        Company company = null;

        if (result.isPresent()) {
            company = result.get();
        } else {
            throw new RuntimeException("Did not find company id - " + id);
        }
        return company;
    }

    @Override
    @Transactional
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    @Transactional
    public List<Integer> findByNameCompanyLike(String keySearch) {
        return companyRepository.findByNameCompanyLike(keySearch);
    }

}
