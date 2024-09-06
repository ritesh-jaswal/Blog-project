package com.example.Blog_project.services;

import com.example.Blog_project.models.Category;
import com.example.Blog_project.models.User;
import com.example.Blog_project.payloads.PostDto;

import java.util.List;

public interface PostService
{
    PostDto createPost(PostDto postDto,Integer userId,Integer catId);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize);
    void deletePostById(Integer postId);

    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostByCategory(Integer catId);
    List<PostDto> searchPosts(String keyword);
}
