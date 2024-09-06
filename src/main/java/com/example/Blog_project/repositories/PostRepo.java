package com.example.Blog_project.repositories;

import com.example.Blog_project.models.Category;
import com.example.Blog_project.models.Post;
import com.example.Blog_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer>
{
    List<Post> findAllByUser(User user);
    List<Post> findAllByCategory(Category category);
}
