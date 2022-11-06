package com.blogapi.blogapi.repository;

import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogapi.blogapi.entity.Role;
import com.blogapi.blogapi.entity.User;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    // JUnit test for testing save user operation
    @Test
    public void givenUserObject_whenSave_thenReturnSavedUser() {
        // Given:- pre condition or setup
        User user = new User();
        user.setName("Ankita Chauhan");
        user.setUsername("ankita26");
        user.setEmail("ankitac26@gmail.com");
        user.setPassword(passwordEncoder.encode("vaibhav"));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        // When :- Action or behavior that we are doing test

        User result = userRepository.save(user);

        // Then :- Verify the output

        Assertions.assertThat(result).isNotNull();

    }
}
