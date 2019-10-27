package com.jxau.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jxau.mapper.AdminMapper;
import com.jxau.mapper.UserMapper;
import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.vo.QueryVo;
import com.jxau.service.AdminService;
import com.jxau.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource(name = "adminMapper")
    private AdminMapper adminMapper;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public void changLocked(String id, String isLocked) {
        userMapper.changeLocked(Integer.parseInt(id), Integer.parseInt(isLocked));

    }

    @Override
    public PageBean findAllUser(int currentPage, int currentCount) {
        Page page = PageHelper.startPage(currentPage, currentCount);

        List<User> users = userMapper.findAllUser();

        //封装PageBean
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setTotalCount(page.getTotal());
        pageBean.setTotalPage(page.getPages());
        pageBean.setCurrentCount(currentCount);
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(users);

        return pageBean;
    }

    @Override
    public PageBean<User> findUserByCondition(QueryVo vo, int currentPage, int currentCount) {

        Page page = PageHelper.startPage(currentPage, currentCount);

        //vo.setUsername("%" + vo.getUsername() + "%");
        List<User> list = userMapper.findUserByCondition(vo);

        //封装pageBean
        PageBean<User> userPageBean = new PageBean<>();
        userPageBean.setTotalCount(page.getTotal());
        userPageBean.setTotalPage(page.getPages());
        userPageBean.setCurrentPage(currentPage);
        userPageBean.setCurrentCount(currentCount);
        userPageBean.setList(list);
        return userPageBean;
    }

    @Override
    public Admin login(Admin admin) {

        return adminMapper.findAdminByNameAndPassword(admin.getUsername(), admin.getPassword());

    }

}


