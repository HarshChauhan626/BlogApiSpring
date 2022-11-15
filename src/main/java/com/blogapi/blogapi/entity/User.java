package com.blogapi.blogapi.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "email" })
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY)
    private List<Followers> followers;

    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY)
    private List<Followers> following;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Like> likes = new HashSet<Like>();

}
