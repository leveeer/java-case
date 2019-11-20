package com.jxau.xw.service.impl;

import com.jxau.xw.dao.impl.AdminDaoImpl;
import com.jxau.xw.domain.Admin;
import com.jxau.xw.service.AdminService;

public class AdminServiceImpl implements AdminService {


    private AdminDaoImpl dao = new AdminDaoImpl();


    @Override
    public Admin login(Admin admin) {
        return dao.findAdminByUsernameAndPassword(admin.getUsername(),admin.getPassword());
    }

    @Override
    public Admin findAdminById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateAdminPassword(String newpassword) {
        dao.updatePassword(newpassword);
    }


}