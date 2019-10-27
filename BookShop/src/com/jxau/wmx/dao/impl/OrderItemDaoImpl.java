package com.jxau.wmx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jxau.wmx.dao.IOrderItemDao;
import com.jxau.wmx.entity.OrderItem;
import com.jxau.wmx.util.C3P0Util;

public class OrderItemDaoImpl implements IOrderItemDao {
     private Connection conn;
     private PreparedStatement ps;
     private ResultSet rs;
	@Override
	public void saveOrderItem(OrderItem orderItem) {
		conn=C3P0Util.getConn();
		String insert_sql="insert into orderitem values(?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(insert_sql);
			ps.setString(1, orderItem.getOid());
			//存入数据的pid
			ps.setInt(2, orderItem.getProduct().getPid());
			//设置购买数量
			ps.setInt(3, orderItem.getBuynum());
			//设置小计
			ps.setDouble(4, orderItem.getSubTotal());
			int result=ps.executeUpdate();
			//此语句为了测试
			System.out.println("生成订单项成功"+result);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn, ps, rs);
		}
		
		
	}

}
