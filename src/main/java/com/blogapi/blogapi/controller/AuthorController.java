package com.blogapi.blogapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.payload.FollowerListResponse;
import com.blogapi.blogapi.payload.FollowingListResponse;
import com.blogapi.blogapi.payload.UserListRequest;
import com.blogapi.blogapi.payload.UserListResponse;
import com.blogapi.blogapi.service.AuthorService;
import com.blogapi.blogapi.service.FollowerService;
import com.blogapi.blogapi.utils.AppConstants;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;
    private FollowerService followerService;

    public AuthorController(AuthorService authorService,FollowerService followerService) {
        this.authorService = authorService;
        this.followerService=followerService;
    }


    // @GetMapping("/list")
    // public ResponseEntity<UserListResponse> getAuthorList( @RequestBody UserListRequest userListRequest){
    //     // return ResponseEntity<AuthorListResponse>;
    //     UserListResponse userListResponse=authorService.getAuthorList( 0, 0, "name", "ASC");
    //     return new ResponseEntity<UserListResponse>(userListResponse,HttpStatus.OK);
    // }

    @GetMapping("/list")
    public ResponseEntity<UserListResponse> getAuthorList(@RequestBody UserListRequest userListRequest){
        if(userListRequest.getSearchText()!=null && !userListRequest.getSearchText().isEmpty()){
            UserListResponse userListResponse=authorService.searchAuthors(userListRequest.getSearchText().orElse(""), userListRequest.getPageNo(), userListRequest.getPageSize(), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
            return new ResponseEntity<UserListResponse>(userListResponse,HttpStatus.OK);
        }
        else{
            UserListResponse userListResponse=authorService.getAuthorList( userListRequest.getPageNo(), userListRequest.getPageSize(), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION);
            return new ResponseEntity<UserListResponse>(userListResponse,HttpStatus.OK);
        }
    }


    @GetMapping("/followers")
    public ResponseEntity<FollowerListResponse> getFollowerList(){
        FollowerListResponse followerListResponse=followerService.getFollowerList();
        return new ResponseEntity<FollowerListResponse>(followerListResponse,HttpStatus.OK);
    }


    @GetMapping("/following")
    public ResponseEntity<FollowingListResponse> getFollowingList(){
        FollowingListResponse followingListResponse=followerService.getFollowingList();
        return new ResponseEntity<FollowingListResponse>(followingListResponse,HttpStatus.OK);
    }


}
