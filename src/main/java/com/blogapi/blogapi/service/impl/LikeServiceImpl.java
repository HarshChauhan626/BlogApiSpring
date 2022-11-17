package com.blogapi.blogapi.service.impl;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blogapi.blogapi.entity.Like;
import com.blogapi.blogapi.entity.Post;
import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.repository.LikeRepository;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.repository.UserRepository;
import com.blogapi.blogapi.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository,
            UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void likePost(long postId) {
        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(userDetails.getName());
        String userName = userDetails.getName();
        Optional<User> user = userRepository.findByUsernameOrEmail(userName, userName);
        Optional<Post> post = postRepository.findById(postId);
        if (user.get() != null) {
            long id = user.get().getId();
            if (likeRepository.findByPostIdAndUserId(postId, id) == null) {
                Like like = new Like();
                like.setUser(user.get());
                like.setPost(post.get());
                likeRepository.save(like);
            }
        }
    }

    @Override
    public void unlikePost(long postId) {
        Authentication userDetails = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(userDetails.getName());
        String userName = userDetails.getName();
        Optional<User> user = userRepository.findByUsernameOrEmail(userName, userName);
        Optional<Post> post = postRepository.findById(postId);
        if (user.get() != null) {
            long id = user.get().getId();
            Optional<Like> like=likeRepository.findByPostIdAndUserId(postId, id);
            if(like!=null){
                likeRepository.delete(like.get());
            }
        }
    }

}
