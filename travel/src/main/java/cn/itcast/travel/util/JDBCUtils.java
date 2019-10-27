package cn.itcast.travel.util;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 连接数据库工具类
 */
public class JDBCUtils {


	private static DataSource ds;


	//将配置文件加载进内存
	static {
		try {
			Properties properties = new Properties();
			InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
			properties.load(resourceAsStream);
			//获取DataSource
			ds = DruidDataSourceFactory.createDataSource(properties);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	/**
	 * 释放资源
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取连接池
	 * @return
	 */
	public static DataSource getDataSource() {
		return ds;
	}
}