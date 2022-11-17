package com.blogapi.blogapi.entity;

import javax.persistence.*;

import lombok.Data;


@Entity
@Table(name = "Likes",uniqueConstraints = {
    @UniqueConstraint(columnNames = {})
})
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",nullable = false)
    private Post post;

}
