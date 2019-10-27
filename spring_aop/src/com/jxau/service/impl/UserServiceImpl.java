package com.jxau.service.impl;

import com.jxau.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void addUser(String name, int age) {
        System.out.println("添加用户：" + name + "---" + age);
    }

    @Override
    public void delete(int id) {
        System.out.println("删除用户");
    }
}
