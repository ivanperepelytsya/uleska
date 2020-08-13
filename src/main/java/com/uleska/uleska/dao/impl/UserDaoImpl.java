package com.uleska.uleska.dao.impl;

import com.uleska.uleska.dao.UserDao;
import com.uleska.uleska.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    private Map<String, User> users = new HashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList(users.values());
    }

    @Override
    public User create(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    @Override
    public User findUserById(String id) {
        return users.get(id);
    }

    @Override
    public User updateUser(User user, String id) {
        return users.put(id, user);

    }

    @Override
    public void deleteUserById(String id) {
        users.remove(id);
    }
}
