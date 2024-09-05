package com.example.Blog_project.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto
{
    private Integer id;

    @NotEmpty(message = "Name cannot Be Empty")
    private String name;

    @Email(message = "Enter a valid Email")
    @NotEmpty(message = "Email cannot be Empty")
    private String email;

    @NotEmpty(message = "Password Cannot be Empty")
//    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$")
    private String password;

    @NotEmpty(message = "About Cannot be Empty")
    private String about;
}
/*
This regular expression enforces the following rules:
(?=.*[a-z]): At least one lowercase letter.
(?=.*[A-Z]): At least one uppercase letter.
(?=.*\\d): At least one digit.
(?=.*[@#$%^&+=]): At least one special character from the set @, #, $, %, ^, &, +, =.
.{8,}: The password must be at least 8 characters long.
You can adjust the length and set of special characters based on your specific security requirements.
 */