package com.jxau.xw.datasource.druid;

import com.jxau.xw.datasource.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用新的工具类
 */
public class DruidDemo02 {
    public static void main(String[] args) {
        /**
         * 完成添加的操作 给student表添加一条记录
         */

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "insert into student values(null,?,?,?,?,?,?)";
            //获取执行sql对象
            pstm = conn.prepareStatement(sql);
            //给?赋值
            pstm.setString(1,"王思聪");
            pstm.setInt(2,27);
            pstm.setString(3,"男");
            pstm.setString(4,"上海");
            pstm.setInt(5,76);
            pstm.setInt(6,83);
            //执行sql
            int result = pstm.executeUpdate();
            System.out.println(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstm,conn);
        }
    }
}
