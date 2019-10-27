package com.jxau.dao;

import com.jxau.domain.Admin;

public interface AdminDao {
    Admin findAdminByNameAndPassword(String name, String password);

    void changeLocked(int parseInt, int parseInt1);
}
