package com.jxau.xw.dao;

import com.jxau.xw.domain.Admin;

/**
 * 管理员操作的Dao
 */
public interface AdminDao {

    Admin findAdminByUsernameAndPassword(String username, String password);

    Admin findById(int id);


    void updatePassword(String password);
}
