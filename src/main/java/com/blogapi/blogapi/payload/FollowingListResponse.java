package com.blogapi.blogapi.payload;

import java.util.List;

import lombok.Data;

@Data
public class FollowingListResponse{
    List<UserDto> following;
}
