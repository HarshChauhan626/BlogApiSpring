package com.blogapi.blogapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Page<User> findByUsernameOrName(String username,String name,Pageable pageable);
    // Page<User> getUserList();
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
