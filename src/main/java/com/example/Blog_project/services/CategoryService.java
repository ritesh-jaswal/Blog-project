package com.example.Blog_project.services;

import com.example.Blog_project.payloads.CategoryDto;

import java.util.List;

public interface CategoryService
{
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer catId);
    CategoryDto getCategoryById(Integer catId);
    List<CategoryDto> getAllCategories();
    void deleteCategoryById(Integer catId);
}
