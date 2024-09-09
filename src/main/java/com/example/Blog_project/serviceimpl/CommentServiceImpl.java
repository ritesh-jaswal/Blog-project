package com.example.Blog_project.serviceimpl;

import com.example.Blog_project.exceptions.ResourceNotFoundException;
import com.example.Blog_project.models.Comment;
import com.example.Blog_project.models.Post;
import com.example.Blog_project.payloads.CommentDto;
import com.example.Blog_project.repositories.CommentRepo;
import com.example.Blog_project.repositories.PostRepo;
import com.example.Blog_project.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService
{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);

        Comment createComment = this.commentRepo.save(comment);
        return this.modelMapper.map(createComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId)
    {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment Id",commentId));
        this.commentRepo.delete(comment);
    }
}
