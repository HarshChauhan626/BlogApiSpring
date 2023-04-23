package com.blogapi.blogapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {
    @GetMapping("/list")
    void getList(){

    }

    @PostMapping()
    void createCollection(){

    }

    @DeleteMapping("{collectionId}")
    void deleteCollection(){
    }

    @PatchMapping("{collectionId}")
    void updateCollection(){

    }

    @GetMapping("/{collectionId}/posts")
    void getCollectionPosts(){
    }

    @PostMapping("/{collectionId}/posts")
    void addPostInCollection(){

    }

    @DeleteMapping("/{collectionId}/posts")
    void removePostFromCollection(){

    }

}
