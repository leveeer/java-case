package com.jxau.xw.dao.impl;

import com.jxau.xw.dao.AdminDao;
import com.jxau.xw.domain.Admin;
import com.jxau.xw.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class AdminDaoImpl implements AdminDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Admin findAdminByUsernameAndPassword(String username, String password) {
        try {
            //定义sql
            String sql = "select * from admin_info where username = ? and password = ?";
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), username, password);
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Admin findById(int id) {
        String sql = "select * from admin_info where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Admin>(Admin.class),id);
    }

    @Override
    public void updatePassword(String newpassword) {
        String sql = "update admin_info set password = ?";
        template.update(sql,newpassword);
    }


}

