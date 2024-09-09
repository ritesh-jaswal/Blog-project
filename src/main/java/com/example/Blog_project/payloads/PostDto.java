package com.example.Blog_project.payloads;

import com.example.Blog_project.models.Category;
import com.example.Blog_project.models.Comment;
import com.example.Blog_project.models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto
{
    private Integer postId;

    @NotEmpty(message = "Title cannot be Empty")
    private String title;

    @NotEmpty(message = "Content cannot be Empty")
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
