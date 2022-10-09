package com.blogapi.blogapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
