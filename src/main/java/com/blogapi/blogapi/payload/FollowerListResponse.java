package com.blogapi.blogapi.payload;

import java.util.List;

import lombok.Data;

@Data
public class FollowerListResponse {
    List<UserDto> followers;
}