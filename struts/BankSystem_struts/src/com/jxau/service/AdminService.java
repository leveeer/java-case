package com.jxau.service;

import com.jxau.domain.Admin;
import com.jxau.domain.User;

import java.util.List;
import java.util.Map;

public interface AdminService {
    Admin login(Admin admin);

    List<User> findAllUser();

    void changLocked(String id, String isLocked);

    List<User> findUser(Map<String, String> condition);
}
