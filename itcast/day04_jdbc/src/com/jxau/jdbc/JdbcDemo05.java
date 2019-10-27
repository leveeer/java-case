package com.jxau.jdbc;

import java.sql.*;

public class JdbcDemo05 {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接数据库对象
            conn = DriverManager.getConnection("jdbc:mysql:///dbtest","root","root");
            //定义sql
            String sql = "select * from student";
            //获取执行sql的对象
            stmt = conn.createStatement();
            //执行sql
            rs = stmt.executeQuery(sql);
            //处理结果
            //让游标向下移动一行
            while(rs.next()) {
                //获取数据

                int id = rs.getInt(1);
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String address = rs.getString("address");
                int math = rs.getInt("math");
                int english = rs.getInt("english");

                System.out.println("<" + id + "," + name + "," + age + "," + sex + "," + address + "," + math + "," + english + ">");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
