package com.example.Blog_project.services;

import com.example.Blog_project.payloads.CommentDto;

public interface CommentService
{
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
