package com.example.Blog_project.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto
{
    private Integer catId;

    @NotEmpty(message = "Title Cannot be Empty")
    private String catTitle;

    @NotEmpty(message = "Description Cannot be Empty")
    private String catDesc;
}
