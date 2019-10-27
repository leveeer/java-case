package com.jxau.dao.impl;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import com.jxau.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            //定义sql
            String sql = "select * from user where username = ? and password = ?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        }catch (Exception e){
            return null;
        }
    }
}
