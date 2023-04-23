package com.blogapi.blogapi.service;

import java.util.List;

import com.blogapi.blogapi.payload.FollowerListResponse;
import com.blogapi.blogapi.payload.FollowingListResponse;

public interface FollowerService {
    void followAuthor(long targetId);
    void unfollowAuthor(long targetId);
    FollowerListResponse getFollowerList();
    FollowingListResponse getFollowingList();
}
