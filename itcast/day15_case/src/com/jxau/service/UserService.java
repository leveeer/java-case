package com.jxau.service;

import com.jxau.domain.PageBean;
import com.jxau.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 添加联系人方法
     * @param user
     */
    void addUser(User user);

    /**
     * 根据id删除user
     * @param id
     */
    void deleteUser(String id);

    /**
     * 根据id查询
     * @return
     */
    User findById(String id);



    /**
     * 修改用户信息
     */
    void updateUser(User user);


    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedUser(String[] ids);


    /**
     * 分页条件查询查询
     * @param _currentPage
     * @param _rows
     * @param condiction
     * @return
     */
    PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condiction);
}
