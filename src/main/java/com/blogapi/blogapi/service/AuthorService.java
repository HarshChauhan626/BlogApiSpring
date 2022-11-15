package com.blogapi.blogapi.service;

import com.blogapi.blogapi.payload.UserListResponse;

public interface AuthorService {
    UserListResponse searchAuthors(String searchText,int pageNo, int pageSize, String sortBy, String sortDir);

    UserListResponse getAuthorList(int pageNo, int pageSize, String sortBy, String sortDir);
}
