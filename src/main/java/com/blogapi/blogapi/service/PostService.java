package com.blogapi.blogapi.service;

import com.blogapi.blogapi.payload.PostDto;


public interface PostService {
    PostDto createPost(PostDto postDto);
}
