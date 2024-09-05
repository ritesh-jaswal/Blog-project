package com.example.Blog_project.serviceimpl;

import com.example.Blog_project.exceptions.ResourceNotFoundException;
import com.example.Blog_project.models.User;
import com.example.Blog_project.payloads.UserDto;
import com.example.Blog_project.repositories.UserRepo;
import com.example.Blog_project.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto)
    {
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = this.userRepo.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));

        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos=users.stream()
                .map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public Void deleteUserById(Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
        this.userRepo.delete(user);
        return null;
    }
}
