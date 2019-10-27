package service.impl;

import service.UserService;

public class UserServiceImplProxy implements UserService {

    private UserServiceImpl userServiceImpl;

    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void add(String name, int age) {
        check();
        userServiceImpl.add(name,age);
        log();
    }

    @Override
    public void delete(int id) {
        System.out.println("删除用户");
    }

    @Override
    public String say() {
        return "hello";
    }

    private void check(){
        System.out.println("用户身份验证");
    }

    private void log(){
        System.out.println("记录日志");
    }
}
