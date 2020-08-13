package com.uleska.uleska.dao;

import com.uleska.uleska.model.user.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User create(User user);

    User findUserById(String id);

    User updateUser(User user, String id);

    void deleteUserById(String id);

}
