package com.jxau.wmx.util;
/**
 * 数据库操作的帮助类
 * @author Administrator
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	/**
	 * 获得连接对象
	 * @return
	 */
	public static Connection getConn() {
		Connection conn=null;
		try {
			//1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2、建立连接
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksystem","root","root");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs) {
		 
		 if(rs!=null) {
			 try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 else if(ps!=null) {
			 try {
				ps.close();
				ps=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 else if(conn!=null) {
			 try {
				conn.close();
				conn=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
}
