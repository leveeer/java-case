package com.jxau.dao;

import com.jxau.domain.User;

import java.sql.SQLException;

/**
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 09:44
 * @Version 1.0
 */
public interface UserDao {
    int register(User user) throws SQLException;

    void active(String activeCode) throws SQLException;
}
