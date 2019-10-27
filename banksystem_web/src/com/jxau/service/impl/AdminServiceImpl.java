package com.jxau.service.impl;

import com.jxau.dao.AdminDao;
import com.jxau.dao.UserDao;
import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.factory.BeanFactory;
import com.jxau.service.AdminService;

import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = (AdminDao) BeanFactory.getBean("adminDao");
    private UserDao userDao = (UserDao) BeanFactory.getBean("userDao");


    @Override
    public Admin login(Admin admin) {

        Admin adminLogin = adminDao.findAdminByNameAndPassword(admin.getUsername(), admin.getPassword());
        //System.out.println(adminLogin);
        return adminLogin;

    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public void changLocked(String id, String isLocked) {
        adminDao.changeLocked(Integer.parseInt(id), Integer.parseInt(isLocked));
    }

    @Override
    public List<User> findUser(Map<String, String[]> condiction) {
         return userDao.findUser(condiction);
    }
}
