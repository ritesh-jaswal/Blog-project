package com.example.Blog_project.repositories;

import com.example.Blog_project.models.Category;
import com.example.Blog_project.models.Post;
import com.example.Blog_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer>
{
    List<Post> findAllByUser(User user);
    List<Post> findAllByCategory(Category category);

    @Query("select p from Post p where p.title like %:key%")
    List<Post> searchByTitle(@Param("key") String title);

}
