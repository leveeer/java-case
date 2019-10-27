package com.jxau.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * student表 删除一条记录
 */
public class JdbcDemo04 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接数据库对象
            conn = DriverManager.getConnection("jdbc:mysql:///dbtest","root","root");
            //定义sql
            String sql = "delete from student where name = '王思聪'";
            //获取执行sql的对象
            stmt = conn.createStatement();
            //执行sql
            int result = stmt.executeUpdate(sql);
            //处理结果
            System.out.println("受影响的行：" + result);
            if(result>0){
                System.out.println("删除成功！");
            }else{
                System.out.println("删除失败！");
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
