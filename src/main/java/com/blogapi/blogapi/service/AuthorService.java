package com.blogapi.blogapi.service;

import com.blogapi.blogapi.payload.AuthorListResponse;

public interface AuthorService {
    AuthorListResponse searchAuthors(String searchText);

    AuthorListResponse getAuthorList();
}
