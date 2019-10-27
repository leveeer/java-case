package com.jxau.wmx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jxau.wmx.dao.IOrderDao;
import com.jxau.wmx.dao.IProductDao;
import com.jxau.wmx.entity.Order;
import com.jxau.wmx.entity.OrderItem;
import com.jxau.wmx.entity.Product;
import com.jxau.wmx.entity.User;
import com.jxau.wmx.util.C3P0Util;

public class OrderDaoImpl implements IOrderDao {
    private Connection conn;
    //private PreparedStatement ps;
    private ResultSet rs;
    IProductDao productDao=new ProductDaoImpl();
	@Override
	public void creatOrder(Order order) {
	  conn=C3P0Util.getConn();
	  PreparedStatement ps=null;
	  String insert_sql="insert into orders values(?,?,?,?,?,?,?,?) ";
	try {
		ps = conn.prepareStatement(insert_sql);
		ps.setString(1, order.getOid());
		ps.setDouble(2, order.getMoney());
		ps.setString(3, order.getAddress());
		ps.setString(4, order.getName());
		ps.setString(5, order.getPhone());
		ps.setInt(6, order.getPaystate());
		//存入数据uid
		ps.setInt(7, order.getUser().getUid());
		//设置下单时间
		ps.setString(8, order.getOrdertime());
		int result=ps.executeUpdate();
		//此打印语句仅为了测试
		System.out.println("生成订单成功"+result);
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		C3P0Util.close(conn, ps, rs);
	}
	 
		
	}
	//找到一个用户下有多个订单
	@Override
	public List<Order> findOrderByUser(User user) {
		conn=C3P0Util.getConn();
		List<Order>orders=new ArrayList<Order>();
		String select_sql="select * from orders where uid=?";
	    PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(select_sql);
			ps.setInt(1, user.getUid());
			rs=ps.executeQuery();
			while(rs.next()) {
			 String oid=rs.getString("oid");
			 //根据订单编号找到的订单
			 Order order=findByOid(oid);
			 order.setOid(oid);
			 order.setMoney(rs.getDouble("money"));
			 order.setAddress(rs.getString("address"));
			 order.setName(rs.getString("name"));
			 order.setPhone(rs.getString("phone"));
			 order.setUser(user);
			 order.setPaystate(rs.getInt("paystate"));
			 Date d=rs.getTimestamp("ordertime");
			 SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 order.setOrdertime(sd.format(d));
			 orders.add(order);
			}
			//System.out.println("dao层获取的数据"+orders);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn, ps, rs);
		}
		return orders;
	}
	//根据上面的oid,去查询订单下的所有订单项（商品）
	@Override
	public Order findByOid(String oid) {
	    conn=C3P0Util.getConn();
	    String select_sql="select * from orderitem  where oid=?";
	    Order order=new Order();
	    PreparedStatement ps=null;
		ResultSet rs=null;
	    try {
			ps=conn.prepareStatement(select_sql);
			ps.setString(1, oid);
			rs=ps.executeQuery();
			while(rs.next()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setOid(oid);
			int pid=rs.getInt("pid");
			//根据pid得到对应商品对象
			Product product=productDao.findById(pid);
			orderItem.setProduct(product);
			orderItem.setBuynum(rs.getInt("buynum"));
			orderItem.setSubTotal(rs.getDouble("subTotal"));
			//一对多的关系
			order.getOrderItems().add(orderItem);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn, ps, rs);
		}
		return order;
	}

}
