package com.jxau.mapper;

import com.jxau.domain.User;
import com.jxau.vo.QueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper {
    /**
     * 用户注册
     *
     * @param user
     */
    void register(User user);

    /**
     * 根据姓名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(User user);

    /**
     * 添加余额
     *
     * @param id
     * @param money
     */
    void updateBalance(User user);

    /**
     * 根据姓名查找用户
     *
     * @param getMoneyUser
     * @return
     */
    User findUserByUsername(String getMoneyUser);

    User findUserById(int id);

    void updatePwdById(@Param("id") int id, @Param("newPwd") String newPwd);

    List<User> findAllUser();

    void changeLocked(@Param("id") int id, @Param("isLocked") int isLocked);

    List<User> findUserByCondition(QueryVo ov);
}
