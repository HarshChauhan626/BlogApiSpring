package com.blogapi.blogapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.blogapi.entity.Like;
import com.blogapi.blogapi.entity.Post;
import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.repository.LikeRepository;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.repository.UserRepository;
import com.blogapi.blogapi.service.LikeService;

@RestController
@RequestMapping("/api/posts/")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping({ "{postId}/likes" })
    void likePost(@PathVariable(value = "postId") long postId) {
        likeService.likePost(postId);
    }

    @DeleteMapping({ "{postId}/likes" })
    void unlikePost(@PathVariable(value = "postId") long postId) {
        likeService.unlikePost(postId);
    }

}
