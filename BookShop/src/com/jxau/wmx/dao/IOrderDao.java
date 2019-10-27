package com.jxau.wmx.dao;

import java.util.List;

import com.jxau.wmx.entity.Order;
import com.jxau.wmx.entity.User;

public interface IOrderDao {

	//生成订单方法
	void creatOrder(Order order);
	//查找某个用户的所有订单
	List<Order>findOrderByUser(User user);
	//根据订单编号查找对应的订单
	Order findByOid(String oid);
	
}
