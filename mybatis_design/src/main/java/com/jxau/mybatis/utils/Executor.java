package com.jxau.mybatis.utils;


import com.jxau.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责执行SQL语句，并且封装结果集
 */
public class Executor {

    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1.取出mapper中的数据
            String queryString = mapper.getQueryString();//select * from user
            String resultType = mapper.getResultType();//com.jxau.domain.User
            System.out.println(resultType);
            Class domainClass = Class.forName(resultType);
            //2.获取PreparedStatement对象
            pstm = conn.prepareStatement(queryString);
            //3.执行SQL语句，获取结果集
            rs = pstm.executeQuery();
            //4.封装结果集
            List<E> list = new ArrayList<E>();//定义返回值
            while(rs.next()) {
                //实例化要封装的实体类对象
                E obj = (E)domainClass.newInstance();

                //取出结果集的元信息：ResultSetMetaData
                ResultSetMetaData rsmd = rs.getMetaData();
                //取出总列数
                int columnCount = rsmd.getColumnCount();
                //遍历总列数
                for (int i = 1; i <= columnCount; i++) {
                    //获取每列的名称，列名的序号是从1开始的
                    String columnName = rsmd.getColumnName(i);
                    //根据得到列名，获取每列的值
                    Object columnValue = rs.getObject(columnName);
                    //给obj赋值：使用Java内省机制（借助PropertyDescriptor实现属性的封装）
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);//要求：实体类的属性和数据库表的列名保持一种
                    //获取它的写入方法
                    Method writeMethod = pd.getWriteMethod();
                    //把获取的列的值，给对象赋值
                    writeMethod.invoke(obj,columnValue);
                }
                //把赋好值的对象加入到集合中
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(pstm,rs);
        }
    }

    public <T> T selectOne(Mapper mapper, Connection connection){
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            //取出mapper中sql语句
            String queryString = mapper.getQueryString();
            //取出mapper中的要封装的结果集类型
            String resultType = mapper.getResultType();
            System.out.println(resultType);
            //获取PreparedStatement对象
            pstm = connection.prepareStatement(queryString);
            //执行sql语句
            rs = pstm.executeQuery();
            //创建要封装的对象
            Class domainClass = Class.forName(resultType);
            System.out.println("##############");
            System.out.println(resultType);
            T obj = (T) domainClass.newInstance();
            //封装结果集
            while (rs.next()){
                //获取所有结果集中的数据
                ResultSetMetaData metaData = rs.getMetaData();
                //获取总列数
                int columnCount = metaData.getColumnCount();
                //遍历
                for (int i = 1; i <= columnCount; i++) {
                    //取出每列的名称
                    String columnName = metaData.getColumnName(i);
                    //根据列名获取值
                    Object columnValue = rs.getObject(columnName);
                    //给obj赋值
                    PropertyDescriptor pd = new PropertyDescriptor(columnName, domainClass);
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(obj,columnValue);
                }
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(pstm, rs);
        }

    }


    private void release(PreparedStatement pstm,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(pstm != null){
            try {
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
