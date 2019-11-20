package com.jxau.service;

import com.jxau.domain.User;

import java.sql.SQLException;

/**
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 09:39
 * @Version 1.0
 */
public interface UserService {
    boolean register(User user) throws SQLException;

    void active(String activeCode) throws SQLException;
}
