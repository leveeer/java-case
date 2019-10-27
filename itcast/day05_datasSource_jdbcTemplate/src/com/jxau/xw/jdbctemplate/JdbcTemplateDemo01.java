package com.jxau.xw.jdbctemplate;

import com.alibaba.druid.util.JdbcUtils;
import com.jxau.xw.datasource.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate入门
 */

public class JdbcTemplateDemo01 {
    public static void main(String[] args) {
        //导入jar
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //调用方法
        String sql = "update student set age = 28 where id = ?";
        int result = template.update(sql, 9);
        System.out.println(result);
    }
}
