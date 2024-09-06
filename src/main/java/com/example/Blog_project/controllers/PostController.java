package com.example.Blog_project.controllers;

import com.example.Blog_project.payloads.ApiResponse;
import com.example.Blog_project.payloads.PostDto;
import com.example.Blog_project.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController
{
    @Autowired
    private PostService postService;

    @PostMapping("user/{userId}/category/{catId}/posts")
    public ResponseEntity<PostDto> createUser(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer catId)
    {
        PostDto create = this.postService.createPost(postDto,userId,catId);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
    {
        List<PostDto> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("category/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId)
    {
        List<PostDto> postDtos = this.postService.getPostByCategory(catId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("posts")
    public ResponseEntity<List<PostDto>> getAllPosts()
    {
        List<PostDto> postDtoList = this.postService.getAllPosts();
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId)
    {
        this.postService.deletePostById(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
    }

    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId)
    {
        PostDto postDto1 = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }
}
