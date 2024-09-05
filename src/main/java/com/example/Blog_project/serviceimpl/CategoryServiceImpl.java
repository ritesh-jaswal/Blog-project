package com.example.Blog_project.serviceimpl;

import com.example.Blog_project.exceptions.ResourceNotFoundException;
import com.example.Blog_project.models.Category;
import com.example.Blog_project.payloads.CategoryDto;
import com.example.Blog_project.repositories.CategoryRepo;
import com.example.Blog_project.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto)
    {
        Category category = this.modelMapper.map(categoryDto,Category.class);
        Category createCat = this.categoryRepo.save(category);

        return this.modelMapper.map(createCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId)
    {
        Category category = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",catId));
        category.setCatTitle(categoryDto.getCatTitle());
        category.setCatDesc(categoryDto.getCatDesc());

        Category updatedCategory=this.categoryRepo.save(category);

        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer catId)
    {
        Category category =  this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",catId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories()
    {
        List<Category> categories= this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos=categories.stream()
                .map(category -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategoryById(Integer catId)
    {
        Category category =  this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",catId));
        this.categoryRepo.delete(category);
    }
}
