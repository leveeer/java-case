package com.jxau.dao.impl;

import com.jxau.dao.AdminDao;
import com.jxau.domain.Admin;
import com.jxau.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdminDaoImpl implements AdminDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Admin findAdminByNameAndPassword(String name, String password) {
        //定义sql
        String sql = "select * from admin where username = ? and password = ?";
        try{
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), name, password);
            //System.out.println(admin);
            return admin;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void changeLocked(int id, int isLocked) {
        String sql = "update user set isLocked = ? where id = ?";
        template.update(sql,isLocked,id);
    }
}
