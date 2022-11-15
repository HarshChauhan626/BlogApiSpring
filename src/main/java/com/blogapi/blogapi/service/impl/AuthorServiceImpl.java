package com.blogapi.blogapi.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.payload.UserDto;
import com.blogapi.blogapi.payload.UserListResponse;
import com.blogapi.blogapi.repository.UserRepository;
import com.blogapi.blogapi.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class AuthorServiceImpl implements AuthorService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public AuthorServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public UserListResponse searchAuthors(String searchText,int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(1, 10, sort);

        Page<User> users = userRepository.findByUsernameOrName("harsh", "harsh",pageable);
        
        List<UserDto> userDtoList=users.stream().map(val->mapToDto(val)).toList();
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setContent(userDtoList);
        userListResponse.setPageNo(1);
        userListResponse.setPageSize(10);
        userListResponse.setTotalElements(10);
        userListResponse.setTotalPages(6);
        userListResponse.setLast(true);
        return userListResponse;
    }

    @Override
    public UserListResponse getAuthorList(int pageNo, int pageSize, String sortBy, String sortDir) {
        
        return null;
    }

    // convert Entity into DTO
    private UserDto mapToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    // convert DTO to entity
    private User mapToEntity(UserDto UserDto) {
        User user = modelMapper.map(UserDto, User.class);
        // Post post = new Post();
        // post.setTitle(UserDto.getTitle());
        // post.setDescription(UserDto.getDescription());
        // post.setContent(UserDto.getContent());
        return user;
    }

}
