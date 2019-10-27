package com.jxau.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jxau.dao.AdminDao;
import com.jxau.domain.Admin;
import com.jxau.util.JDBCUtils;

public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void changeLocked(int id, int isLocked) {
        String sql = "update user set isLocked = ? where id = ?";

        template.update(sql, isLocked, id);
    }

    @Override
    public Admin findAdminByNameAndPassword(String name, String password) {

        // 定义sql
        String sql = "select * from admin where username = ? and password = ?";

        try {
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), name, password);

            return admin;
        } catch (Exception e) {
            return null;
        }
    }
}



