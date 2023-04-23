package com.blogapi.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.blogapi.entity.Followers;

public interface FollowerRepository extends JpaRepository<Followers,Long>{
        Optional<Followers> findBySourceIdOrTargetId(long from,long to);
}
