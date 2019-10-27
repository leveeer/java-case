package com.jxau.jdbc;

import com.jxau.domain.Student;
import com.jxau.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 定义一个方法，查询student表的数据将其封装为对象，然后装载集合，返回。
 */
public class JdbcDemo07 {
    public static void main(String[] args) {
        List<Student> list = new JdbcDemo07().findAll2();
        /*//迭代器遍历集合
        Iterator<Student> it = list.iterator();
        while(it.hasNext()){
            Student student = it.next();
            System.out.println(student);
        }*/
        //增强for遍历集合
        for (Student student:list) {
            System.out.println(student);
        }


        //System.out.println(list);
    }
    /**
     * 演示JDBC工具类
     * @return
     */
    public List<Student> findAll2(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet  rs = null;
        List<Student> list = null;
        try {
            //通过配置文件注册驱动，获取连接的对象
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "select * from student";
            //获取执行sql的l对象
             stmt = conn.createStatement();
            //执行sql
             rs = stmt.executeQuery(sql);
            Student student = null;
            list = new ArrayList<Student>();
            //处理结果,遍历结果集，封装对象，装载集合
            while(rs.next()){
                //获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String address = rs.getString("address");
                int math = rs.getInt("math");
                int english = rs.getInt("english");
                //创建student对象并赋值
                student= new Student();
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                student.setSex(sex);
                student.setAddress(address);
                student.setMath(math);
                student.setEnglish(english);
                //装载集合
                list.add(student);
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {

           JDBCUtils.close(rs,stmt,conn);
        }
        return list;
    }


    /**
     * 查询所有student对象
     * @return
     */
    public List<Student> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet  rs = null;
        List<Student> list = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///dbtest","root","root");
            //conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "select * from student";
            //获取执行sql的l对象

            stmt = conn.createStatement();
            //执行sql
            rs = stmt.executeQuery(sql);
            Student student = null;
            //创建一个Student对象,用于
            list = new ArrayList<Student>();
            //处理结果,遍历结果集，封装对象，装载集合
            while(rs.next()){
                //获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String address = rs.getString("address");
                int math = rs.getInt("math");
                int english = rs.getInt("english");
                //创建student对象并赋值
                student= new Student();
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                student.setSex(sex);
                student.setAddress(address);
                student.setMath(math);
                student.setEnglish(english);

                //装载集合
                list.add(student);
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
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
            // JDBCUtils.close(rs,stmt,conn);
        }
        return list;
    }
}
