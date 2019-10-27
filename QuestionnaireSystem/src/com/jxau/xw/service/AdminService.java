package com.jxau.xw.service;

import com.jxau.xw.domain.Admin;

public interface AdminService {


    /**
     * 登录方法
     *
     * @param admin
     * @return
     */
    Admin login(Admin admin);

    Admin findAdminById(String id);

    void updateAdminPassword(String newpassword);
}
