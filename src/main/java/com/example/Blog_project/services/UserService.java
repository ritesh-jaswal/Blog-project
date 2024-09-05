package com.example.Blog_project.services;

import com.example.Blog_project.payloads.UserDto;

import java.util.List;

public interface UserService
{
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUserById(Integer userId);
}
