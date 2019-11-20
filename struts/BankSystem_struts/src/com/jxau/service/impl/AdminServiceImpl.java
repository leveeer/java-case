package com.jxau.service.impl;

import java.util.List;
import java.util.Map;

import com.jxau.dao.AdminDao;
import com.jxau.dao.UserDao;
import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.factory.BeanFactory;
import com.jxau.service.AdminService;

public class AdminServiceImpl implements AdminService {
    private static AdminServiceImpl instance;
    private AdminDao                adminDao = (AdminDao) BeanFactory.getBean("adminDao");
    private UserDao                 userDao  = (UserDao) BeanFactory.getBean("userDao");

    private AdminServiceImpl() {}

    @Override
    public void changLocked(String id, String isLocked) {
        adminDao.changeLocked(Integer.parseInt(id), Integer.parseInt(isLocked));
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public List<User> findUser(Map<String, String> condition) {
        return userDao.findUser(condition);
    }

    @Override
    public Admin login(Admin admin) {
        Admin adminLogin = adminDao.findAdminByNameAndPassword(admin.getUsername(), admin.getPassword());

        return adminLogin;
    }

    public static synchronized AdminServiceImpl getInstance() {
        if (instance == null) {
            instance = new AdminServiceImpl();
        }

        return instance;
    }
}


