package com.jxau.service.impl;

import com.jxau.dao.UserDao;
import com.jxau.dao.impl.UserDaoImpl;
import com.jxau.domain.User;
import com.jxau.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User findUserByUsernameAndPassword(User user) {
        return userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
