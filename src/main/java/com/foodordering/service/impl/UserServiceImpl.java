package com.foodordering.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodordering.entity.Authority;
import com.foodordering.entity.User;
import com.foodordering.repository.AuthorityRepository;
import com.foodordering.repository.UserRepository;
import com.foodordering.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Authority authorities = new Authority();
        authorities = authorityRepository.findByAuthority("ADMIN");
        user.setAuthorityId(authorities);

        return userRepository.save(user);
    }
}
