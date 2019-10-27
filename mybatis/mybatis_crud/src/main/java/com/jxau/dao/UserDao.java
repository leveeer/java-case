package com.jxau.dao;

import com.jxau.domain.QueryVo;
import com.jxau.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    void save(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User findById(Integer id);

    List<User> findByName(String username);

    int findTotal();

    /**
     * 根据queryVo中的查询条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据提供的id集合查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
