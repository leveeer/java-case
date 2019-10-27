package com.jxau.dao;

import com.jxau.domain.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    /**
     * 根据姓名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 添加余额
     * @param id
     * @param money
     */
    void addBalance(int id, BigDecimal money);

    /**
     * 根据姓名查找用户
     * @param getMoneyUser
     * @return
     */
    User findUserByUsername(String getMoneyUser);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser();

    List<User> findUser(Map<String, String> condition);

    User findUserById(int id);

    void updatePwdById(int id, String newPwd);
}
