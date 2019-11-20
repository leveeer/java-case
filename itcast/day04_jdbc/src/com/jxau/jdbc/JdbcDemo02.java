package com.jxau.jdbc;

import java.sql.*;

/**
 * student 表添加一条记录  insert 语句
 */
public class JdbcDemo02 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取执行数据库连接对象
             conn = DriverManager.getConnection("jdbc:mysql:///dbtest", "root", "root");
            //定义sql
            String sql = "insert into student values(null,'王思聪',28,'男','上海',65,67)";
            //获取执行sql的Statement对象
            stmt = conn.createStatement();
            //执行sql语句
            int result = stmt.executeUpdate(sql);
            //处理结果
            System.out.println("受影响的行数：" + result);
            if(result>0){
                System.out.println("添加成功！");
            }else{
                System.out.println("添加失败！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            //为避免空指针异常先进行判断
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
