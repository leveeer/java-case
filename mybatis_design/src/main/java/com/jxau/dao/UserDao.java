package com.jxau.dao;

import com.jxau.domain.User;
import com.jxau.mybatis.annotations.Select;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 查询一个
     * @return
     */
    @Select("select * from user where id = 45")
    User findOne();
}
