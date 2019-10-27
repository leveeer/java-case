package com.jxau.dao;

import com.jxau.domain.User;

public interface UserDao {

    void addUser(User user);
    void deleteUser(User user);
}
