package com.jxau.dao.impl;

import com.jxau.dao.ProvinceDao;
import com.jxau.domain.Province;
import com.jxau.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    //声明成员变量 jdbctemplate
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        //定义sql
        String sql = "select * from province";
        List<Province> provinces = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return provinces;
    }
}
