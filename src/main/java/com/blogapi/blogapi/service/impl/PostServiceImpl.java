package com.blogapi.blogapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blogapi.blogapi.entity.Post;
import com.blogapi.blogapi.exception.ResourceNotFoundException;
import com.blogapi.blogapi.payload.PostDto;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.blogapi.blogapi.payload.PostListResponse;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // Convert DTO to entity
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);

        // Convert Entity to DTO

        PostDto postResponse = mapToDto(savedPost);
        return postResponse;
    }

    @Override
    public PostListResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostListResponse postResponse = new PostListResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    // convert Entity into DTO
    private PostDto mapToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    @Override
    public PostListResponse searchPosts(String searchText, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        System.out.println("Search text coming is" + searchText);

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        try {

            Page<Post> posts = postRepository.searchPost(searchText, pageable);

            // get content for page object
            List<Post> listOfPosts = posts.getContent();

            List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

            PostListResponse postResponse = new PostListResponse();
            postResponse.setContent(content);
            postResponse.setPageNo(posts.getNumber());
            postResponse.setPageSize(posts.getSize());
            postResponse.setTotalElements(posts.getTotalElements());
            postResponse.setTotalPages(posts.getTotalPages());
            postResponse.setLast(posts.isLast());

            // List<Post>
            // listOfPosts=postRepository.findByTitleOrContentOrDescription(searchText,searchText,searchText);

            System.out.println(listOfPosts);
            return postResponse;
        } catch (Exception e) {
            System.out.println("Exception coming is " + e.toString());
        }

        return null;
    }
}
