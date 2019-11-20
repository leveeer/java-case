package com.jxau.service.impl;

import com.jxau.service.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public boolean login(String username, String password) {
        if ("admin".equals(username) && "admin".equals(password)){
            return true;
        }else {
            return false;
        }
    }
}
