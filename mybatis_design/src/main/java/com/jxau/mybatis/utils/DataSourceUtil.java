package com.jxau.mybatis.utils;

import com.jxau.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtil {
    public static Connection getConnection(Configuration cfg) {

        Connection connection = null;
        try {
            //注册驱动
            Class.forName(cfg.getDriver());
            //获取连接
            connection = DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
