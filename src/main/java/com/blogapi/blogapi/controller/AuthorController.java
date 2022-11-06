package com.blogapi.blogapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.payload.AuthorListResponse;
import com.blogapi.blogapi.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/list")
    public ResponseEntity<AuthorListResponse> getAuthorList(){
        // return ResponseEntity<AuthorListResponse>;
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<AuthorListResponse> searchAuthor(){
        return null;
    }


}
