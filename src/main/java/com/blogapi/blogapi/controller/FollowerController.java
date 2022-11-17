package com.blogapi.blogapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/followers")
public class FollowerController {
    @PostMapping("/{targeId}")
    void followAuthor(){
        
    }

    @PostMapping("/{targetId}")
    void unfollowAuthor(){

    }

}
