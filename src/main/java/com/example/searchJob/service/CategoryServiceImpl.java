package com.example.asm2.service;

import com.example.asm2.dao.CategoryRepository;
import com.example.asm2.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
