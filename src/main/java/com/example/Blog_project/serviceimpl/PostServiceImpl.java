package com.example.Blog_project.serviceimpl;

import com.example.Blog_project.exceptions.ResourceNotFoundException;
import com.example.Blog_project.models.Category;
import com.example.Blog_project.models.Post;
import com.example.Blog_project.models.User;
import com.example.Blog_project.payloads.PostDto;
import com.example.Blog_project.repositories.CategoryRepo;
import com.example.Blog_project.repositories.PostRepo;
import com.example.Blog_project.repositories.UserRepo;
import com.example.Blog_project.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService
{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer catId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserID",userId));
        Category category = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",catId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post create=this.postRepo.save(post);
        return this.modelMapper.map(create,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post update = this.postRepo.save(post);
        return this.modelMapper.map(update, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts()
    {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public void deletePostById(Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
        List<Post> posts = this.postRepo.findAllByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer catId)
    {
        Category category = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",catId));
        List<Post> posts = this.postRepo.findAllByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return List.of();
    }
}
