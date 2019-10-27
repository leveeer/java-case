package service.impl;

import service.UserService;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("UserServiceImpl对象创建了");
    }

    @Override
    public void add(String name, int age) {
        System.out.println("添加用户:" + name + "-----" + age);
    }

    @Override
    public void delete(int id) {
        System.out.println("删除用户");
    }

    @Override
    public String say() {

        return "hello";
    }


}
