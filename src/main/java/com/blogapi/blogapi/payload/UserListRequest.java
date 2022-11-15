package com.blogapi.blogapi.payload;

import java.util.Optional;

import lombok.Data;

@Data
public class UserListRequest{
    private int pageNo;
    private int pageSize;
    private String sortDirection;
    private String sortBy;
    private Optional<String> searchText;
}