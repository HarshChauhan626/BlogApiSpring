package com.blogapi.blogapi.service;

import com.blogapi.blogapi.payload.PostDto;

import com.blogapi.blogapi.payload.PostListResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostListResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);

    PostListResponse searchPosts(String searchText, int pageNo, int pageSize, String sortBy, String sortDir);
}
