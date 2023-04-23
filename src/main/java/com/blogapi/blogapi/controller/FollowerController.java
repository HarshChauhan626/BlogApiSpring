package com.blogapi.blogapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.blogapi.service.FollowerService;

@RestController
@RequestMapping("/api/followers")
public class FollowerController {

    private FollowerService followerService;

    FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @PostMapping("/{targetId}")
    void followAuthor(@PathVariable(value = "targetId") long targetId) {
        followerService.followAuthor(targetId);
    }

    @DeleteMapping("/{targetId}")
    void unfollowAuthor(@PathVariable(value = "targetId") long targetId) {
        followerService.unfollowAuthor(targetId);
    }

}
