package com.blogapi.blogapi.service;

public interface LikeService {
    void likePost(long postId);
    void unlikePost(long postId);
}
