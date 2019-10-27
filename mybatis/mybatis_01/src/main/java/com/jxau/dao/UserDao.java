package com.jxau.dao;

import com.jxau.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
