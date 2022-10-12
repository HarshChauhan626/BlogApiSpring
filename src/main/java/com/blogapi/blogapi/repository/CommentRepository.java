package com.blogapi.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long postId);
}

