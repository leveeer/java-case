package com.jxau.service;

import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.vo.QueryVo;
import com.jxau.util.PageBean;


public interface AdminService {

    Admin login(Admin admin);

    PageBean findAllUser(int currentPage, int currentCount);

    void changLocked(String id, String isLocked);

    PageBean findUserByCondition(QueryVo vo, int currentPage, int currentCount);
}
