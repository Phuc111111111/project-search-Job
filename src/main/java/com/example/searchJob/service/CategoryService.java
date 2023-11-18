package com.example.searchJob.service;



import java.util.List;

import com.example.searchJob.entity.Category;

public interface CategoryService {
    List<Category> getTop4Category();

    List<Category> findAll();
}
