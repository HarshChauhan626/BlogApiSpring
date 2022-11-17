package com.blogapi.blogapi.service;

public interface FollowerService {
    void followAuthor(long targetId);
    void unfollowAuthor(long targetId);
}
