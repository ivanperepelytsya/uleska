package com.uleska.uleska.service;

import com.uleska.uleska.model.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User create(User user);

    User findUserById(String id);

    User updateUser(User user, String id);

    void deleteUserById(String id);

}
