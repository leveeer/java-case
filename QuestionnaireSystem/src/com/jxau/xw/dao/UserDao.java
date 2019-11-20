package com.jxau.xw.dao;

import com.jxau.xw.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 查询所有注册用户
     * @return
     */
    List<User> findAll();

    /**
     * 删除用户信息
     * @param id
     */
    void delete(int id);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 修改密码
     * @param user
     */
    void updatePassword(User user);
}
