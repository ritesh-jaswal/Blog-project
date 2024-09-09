package com.example.Blog_project.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto
{
    private Integer commentId;

    private String content;
}
