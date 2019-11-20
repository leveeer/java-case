package com.jxau.dao;

import com.jxau.domain.User;

public interface UserDao {

    public abstract User findUserByUsernameAndPassword(String username, String password);




}
