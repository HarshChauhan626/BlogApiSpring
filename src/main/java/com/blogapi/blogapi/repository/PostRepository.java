package com.blogapi.blogapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blogapi.blogapi.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(nativeQuery = true, value = "SELECT *" + "FROM posts "
            + "WHERE (posts.title like CONCAT('%', ?1, '%') or posts.content like CONCAT('%', ?1, '%') or posts.description like CONCAT('%', ?1, '%'))")
    Page<Post> searchPost(String searchText, Pageable pageable);
}
