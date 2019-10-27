package com.jxau.xw.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo02 {
    public static void main(String[] args) throws SQLException {
        //获取DataSource.使用默认配置
        DataSource ds = new ComboPooledDataSource();
         for (int i = 1;i<=10;i++){
             Connection conn = ds.getConnection();
             System.out.println(i+"地址"+conn);

             if(i==5){
                 conn.close();
             }
        }

    }
}
