package com.jxau.service;

import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;
import java.util.Map;

public interface AdminService {
    Admin login(Admin admin);

    PageBean findAllUser(int currentPage, int currentCount);

    void changLocked(String id, String isLocked);

    List<User> findUser(Map<String, String> condition);

    PageBean<User> findUserByCondition(DetachedCriteria dc, int currentPage, int currentCount);
}
