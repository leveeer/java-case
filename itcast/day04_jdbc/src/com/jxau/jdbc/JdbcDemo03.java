package com.jxau.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
    student表 修改记录
 */
public class JdbcDemo03 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///dbtest", "root", "root");
            //定义sql
            String sql = "update student set age = 25 where name = '王思聪'";
            //获取执行sql的对象
            stmt = conn.createStatement();
            //执行sql
            int result = stmt.executeUpdate(sql);
            System.out.println("受影响的行：" + result);
            if(result>0){
                System.out.println("修改成功！");
            }else{
                System.out.println("修改失败！");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
