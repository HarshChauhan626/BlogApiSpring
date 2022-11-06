package com.blogapi.blogapi.payload;

import lombok.Data;

@Data
public class AuthorDto {
    private long id;
    private String name;
    private String username;
    private String email;
}