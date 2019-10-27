package com.jxau.xw.service;

import com.jxau.xw.domain.User;

import java.util.List;

public interface UserService {

    void userRegister(User user);

    User login(User user);

    List<User> findAll();

    void deleteUser(String id);

    void updateUser(User user);

    User findUserById(String id);

    void updateUserPassword(User user);
}
