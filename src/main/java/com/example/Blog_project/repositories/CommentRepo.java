package com.example.Blog_project.repositories;

import com.example.Blog_project.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
