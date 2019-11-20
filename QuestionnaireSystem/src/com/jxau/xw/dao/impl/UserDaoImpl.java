package com.jxau.xw.dao.impl;

import com.jxau.xw.dao.UserDao;
import com.jxau.xw.domain.User;
import com.jxau.xw.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public void register(User user) {

        //定义sql
        String sql = "insert into user_info values(null,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getTel(),user.getGender(),user.getAge());
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try{
            //定义sql
            String sql = "select * from user_info where username = ? and password = ?";
            User users = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return users;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<User> findAll() {
        //定义sql
        String sql = "select * from user_info";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public void delete(int id) {
        //定义sql
        String sql = "delete from user_info where id = ?";
        template.update(sql,id);
    }

    @Override
    public void update(User user) {
        //定义sql
        String sql = "update user_info set username = ?,gender = ?,age = ?,tel = ?,email = ? where id = ?";
        template.update(sql,user.getUsername(),user.getGender(),user.getAge(),user.getTel(),user.getEmail(),user.getId());
    }

    @Override
    public User findById(int id) {
        //定义sql
        String sql = "select * from user_info where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void updatePassword(User user) {
        String sql = "update user_info set password = ? where id = ?";
        template.update(sql,user.getPassword(),user.getId());
    }


}
