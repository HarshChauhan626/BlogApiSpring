package com.blogapi.blogapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blogapi.blogapi.entity.Followers;
import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.repository.FollowerRepository;
import com.blogapi.blogapi.repository.UserRepository;
import com.blogapi.blogapi.security.CustomUserDetailsService;
import com.blogapi.blogapi.payload.FollowerListResponse;
import com.blogapi.blogapi.payload.FollowingListResponse;
import com.blogapi.blogapi.payload.UserDto;
import com.blogapi.blogapi.service.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService {

    private CustomUserDetailsService customUserDetailsService;
    private UserRepository userRepository;
    private FollowerRepository followerRepository;
    private ModelMapper modelMapper;

    FollowerServiceImpl(CustomUserDetailsService customUserDetailsService, UserRepository userRepository,
            FollowerRepository followerRepository, ModelMapper modelMapper) {
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void followAuthor(long targetId) {
        Optional<User> currentUser = customUserDetailsService.getUser();
        Optional<User> targetUser = userRepository.findById(targetId);

        Optional<Followers> followersVal = followerRepository.findBySourceIdOrTargetId(currentUser.get().getId(),
                targetId);

        boolean isAlreadyFollowed = false;

        if (!followersVal.isEmpty()) {
            isAlreadyFollowed = true;
        }

        if (currentUser != null && targetUser != null && currentUser.get().getId() != targetUser.get().getId()
                && !isAlreadyFollowed) {
            Followers followers = new Followers();
            followers.setSource(currentUser.get());
            followers.setTarget(targetUser.get());
            followerRepository.save(followers);
        }

    }

    @Override
    public void unfollowAuthor(long targetId) {
        Optional<User> currentUser = customUserDetailsService.getUser();
        Optional<User> targetUser = userRepository.findById(targetId);

        Optional<Followers> followersVal = followerRepository.findBySourceIdOrTargetId(currentUser.get().getId(),
                targetId);

        boolean isAlreadyFollowed = false;

        if (!followersVal.isEmpty()) {
            isAlreadyFollowed = true;
        }

        if (currentUser != null && targetUser != null && currentUser.get().getId() != targetUser.get().getId()
                && isAlreadyFollowed) {

            followerRepository.delete(followersVal.get());
        }
    }

    @Override
    public FollowerListResponse getFollowerList() {
        Optional<User> currentUser = customUserDetailsService.getUser();
        User currentUserVal = currentUser.get();

        List<Followers> followerList = currentUserVal.getFollowers();
        List<UserDto> userFollowerList = new ArrayList<UserDto>();
        if (followerList.size() > 0) {
            userFollowerList = followerList.stream()
                    .map((follower) -> modelMapper.map(follower.getSource(), UserDto.class)).toList();
        }
        FollowerListResponse followerListResponse = new FollowerListResponse();
        followerListResponse.setFollowers(userFollowerList);
        return followerListResponse;
    }

    @Override
    public FollowingListResponse getFollowingList() {
        Optional<User> currentUser = customUserDetailsService.getUser();
        User currentUserVal = currentUser.get();

        List<Followers> followingList = currentUserVal.getFollowing();
        List<UserDto> userFollowingList = new ArrayList<UserDto>();
        if (followingList.size() > 0) {
            userFollowingList = followingList.stream()
                    .map((follower) -> modelMapper.map(follower.getTarget(), UserDto.class)).toList();
        }
        FollowingListResponse followingListResponse = new FollowingListResponse();
        followingListResponse.setFollowing(userFollowingList);
        return followingListResponse;
    }

}
