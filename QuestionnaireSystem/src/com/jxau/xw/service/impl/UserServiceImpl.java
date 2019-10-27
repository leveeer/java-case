package com.jxau.xw.service.impl;

import com.jxau.xw.dao.impl.UserDaoImpl;
import com.jxau.xw.domain.User;
import com.jxau.xw.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoImpl dao = new UserDaoImpl();
    @Override
    public void userRegister(User user) {
        dao.register(user);
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUserPassword(User user) {
        dao.updatePassword(user);
    }
}
