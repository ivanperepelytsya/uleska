package com.uleska.uleska.service.impl;

import com.uleska.uleska.dao.UserDao;
import com.uleska.uleska.model.exception.UException;
import com.uleska.uleska.model.user.User;
import com.uleska.uleska.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User create(User user) {
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getSurname())){
            throw new UException("Mandatory fields are empty");
        }
        userDao.create(user);
        return user;
    }

    @Override
    public User findUserById(String id) {
        User userById = userDao.findUserById(id);
        if (userById == null){
            throw new UException("User not found");
        }
        return userById;
    }

    @Override
    public User updateUser(User user, String id) {
        User userById = this.findUserById(id);
        userDao.updateUser(user, id);
        return null;
    }

    @Override
    public void deleteUserById(String id) {
        userDao.deleteUserById(id);
    }
}
