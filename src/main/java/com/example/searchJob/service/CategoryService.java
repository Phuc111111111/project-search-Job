package com.example.asm2.service;

import com.example.asm2.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getTop4Category();

    List<Category> findAll();
}
