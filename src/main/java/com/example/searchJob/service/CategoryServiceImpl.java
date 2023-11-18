package com.example.searchJob.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.searchJob.dao.CategoryRepository;
import com.example.searchJob.entity.Category;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getTop4Category() {
        return categoryRepository.getTop4Category();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll(Sort.by("name"));
    }
}
