package com.blogapi.blogapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    List<User> findByUsernameOrName(String username,String name);
    // Page<User> getUserList();
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
