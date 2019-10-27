package com.jxau.service.impl;

import com.jxau.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String username, String password) {
        if ("admin".equals(username) && "admin".equals(password)){
            System.out.println("登录成功");
            return true;
        }else {
            return false;
        }
    }
}
