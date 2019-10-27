package com.jxau.service.impl;

import com.jxau.dao.UserDao;
import com.jxau.dao.impl.UserDaoImpl;
import com.jxau.domain.User;
import com.jxau.service.UserService;

import java.sql.SQLException;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 09:40
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {

    @Override
    public boolean register(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        int result = userDao.register(user);
        return result > 0;
    }

    @Override
    public void active(String activeCode) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        userDao.active(activeCode);
    }
}
