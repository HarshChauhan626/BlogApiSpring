package com.blogapi.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
}
