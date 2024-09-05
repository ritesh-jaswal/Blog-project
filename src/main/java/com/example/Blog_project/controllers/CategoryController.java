package com.example.Blog_project.controllers;

import com.example.Blog_project.payloads.ApiResponse;
import com.example.Blog_project.payloads.CategoryDto;
import com.example.Blog_project.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto categoryDto1 = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
    {
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Integer catId)
    {
        this.categoryService.deleteCategoryById(catId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId)
    {
        CategoryDto categoryDto = this.categoryService.getCategoryById(catId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
       List<CategoryDto> categoryDtos = this.categoryService.getAllCategories();
       return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }
}
