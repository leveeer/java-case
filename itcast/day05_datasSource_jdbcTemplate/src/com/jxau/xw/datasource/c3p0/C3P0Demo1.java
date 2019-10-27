package com.jxau.xw.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0的演示
 */
public class C3P0Demo1 {
    public static void main(String[] args) throws SQLException {
        //使用默认配置
        //创建数据库连接池对象
        //DataSource ds = new ComboPooledDataSource();
        //使用指定名称配置
        DataSource ds = new ComboPooledDataSource("otherc3p0");
        //获取连接对象
        Connection conn = ds.getConnection();

        System.out.println(conn);



    }
}
