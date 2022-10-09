package com.blogapi.blogapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.blogapi.payload.PostDto;
import com.blogapi.blogapi.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;
    
    public PostController(PostService postService){
        this.postService=postService;
    }

    // Create blog post

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<PostDto>(postService.createPost(postDto),HttpStatus.CREATED);
    }

}
