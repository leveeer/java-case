package com.jxau.dao.impl;

import com.jxau.dao.ProvinceDao;
import com.jxau.domain.Province;
import com.jxau.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        String sql = "select * from province";
        List<Province> provinces = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return provinces;
    }
}
