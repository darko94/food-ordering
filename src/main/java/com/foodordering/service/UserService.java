package com.foodordering.service;

import com.foodordering.entity.User;

public interface UserService {
    public User findUserByEmail(String email);
    public User saveUser(User user);
}
