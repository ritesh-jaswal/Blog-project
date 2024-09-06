package com.example.Blog_project.controllers;

import com.example.Blog_project.configs.AppConstants;
import com.example.Blog_project.payloads.ApiResponse;
import com.example.Blog_project.payloads.ImageResponse;
import com.example.Blog_project.payloads.PostDto;
import com.example.Blog_project.payloads.PostResponse;
import com.example.Blog_project.services.FileService;
import com.example.Blog_project.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/")
public class PostController
{
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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

//    @GetMapping("posts")
//    public ResponseEntity<List<PostDto>> getAllPosts
//            (@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
//             @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize)
//    {
//        List<PostDto> postDtoList = this.postService.getAllPosts(pageNumber,pageSize);
//        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
//    }

    @GetMapping("posts")
    public ResponseEntity<PostResponse> getAllPosts
            (@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
             @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
             @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
             @RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIRECTION,required = false)String sortDirection)
    {
        PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDirection);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
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

    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword")String keyword)
    {
        List<PostDto> postDtos = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //UPLOADING IMAGE
    @PostMapping("posts/image/upload/{postId}")
    public ResponseEntity<ImageResponse> uploadPostImage
    (@RequestParam("image")MultipartFile image,
     @PathVariable Integer postId) throws IOException
    {
        PostDto postDto = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        this.postService.updatePost(postDto,postId);

        return new ResponseEntity<>(new ImageResponse(fileName,"Image Uploaded Successfully"),HttpStatus.OK);
    }

    //SERVING IMAGE
    @GetMapping(value = "posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException
    {
        InputStream is = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is,response.getOutputStream());
    }
}
