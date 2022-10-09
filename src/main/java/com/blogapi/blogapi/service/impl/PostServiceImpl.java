package com.blogapi.blogapi.service.impl;

import org.springframework.stereotype.Service;

import com.blogapi.blogapi.entity.Post;
import com.blogapi.blogapi.payload.PostDto;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // Convert DTO to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepository.save(post);

        // Convert Entity to DTO

        PostDto postResponse = new PostDto();

        postResponse.setId(savedPost.getId());
        postResponse.setTitle(savedPost.getTitle());
        postResponse.setDescription(savedPost.getDescription());
        postResponse.setContent(savedPost.getContent());

        return postResponse;
    }
}
