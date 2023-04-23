package com.blogapi.blogapi.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blogapi.blogapi.entity.Like;
import com.blogapi.blogapi.entity.Post;
import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.repository.LikeRepository;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.repository.UserRepository;
import com.blogapi.blogapi.security.CustomUserDetailsService;
import com.blogapi.blogapi.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;
    private PostRepository postRepository;
    private CustomUserDetailsService customUserDetailsService;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository,
            CustomUserDetailsService customUserDetailsService) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void likePost(long postId) {
        Optional<User> currentUser = customUserDetailsService.getUser();
        Optional<Post> post = postRepository.findById(postId);
        if (currentUser.get() != null) {
            long id = currentUser.get().getId();
            if (likeRepository.findByPostIdAndUserId(postId, id) == null) {
                Like like = new Like();
                like.setUser(currentUser.get());
                like.setPost(post.get());
                likeRepository.save(like);
            }
        }
    }

    @Override
    public void unlikePost(long postId) {
        Optional<User> currentUser = customUserDetailsService.getUser();
        if (currentUser.get() != null) {
            long id = currentUser.get().getId();
            Optional<Like> like = likeRepository.findByPostIdAndUserId(postId, id);
            if (like != null) {
                likeRepository.delete(like.get());
            }
        }
    }

}
