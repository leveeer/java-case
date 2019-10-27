package com.jxau.jdbc;

import com.jxau.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 练习
 *      需求：
 *          1、通过键盘录入用户名和密码
 *          2、判断用户是否登录成功
 */

public class JDBCDemo08 {

    public static void main(String[] args) {
        //1.键盘录入,接收用户名和密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        //2.调用方法
        /*JDBCDemo08 jd = new JDBCDemo08();
        jd.login(username,password);*/
        boolean flag = new JDBCDemo08().login2(username, password);

        //3.判断结果，输出不同语句
        if(flag){
            System.out.println("登录成功！");
        }else{
            System.out.println("登录失败！用户名或密码错误！");
        }
    }

    /**
     * 登录方法
     */
    public boolean login(String username, String password){
        if(username==null || password==null){
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = '"+ username +"' and password = '"+ password+"'";
            //如果这样定义的话，用户名随意输入，密码输入：a' or 'a' = 'a 也可以登录成功，会造成sql注入问题
            //3.获取执行sql的对象
            stmt = conn.createStatement();
            //4.执行sql查询
            rs = stmt.executeQuery(sql);
            //5.判断
            /*if(rs.next()){//如果有下一行，则返回true
                return true;
            }else{
                return false;
            }*/
            return rs.next();//如果有下一行，则返回true
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,stmt,conn);
        }

        return false;
    }


    /**
     * 登录方法,使用PreparedStatement实现
     */
    public boolean login2(String username, String password){
        if(username==null || password==null){
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = ? and password = ?";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给?赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //4.执行sql查询,不需要传参
            rs = pstmt.executeQuery();
            //5.判断
            /*if(rs.next()){//如果有下一行，则返回true
                return true;
            }else{
                return false;
            }*/
            return rs.next();//如果有下一行，则返回true
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }

        return false;
    }
}
