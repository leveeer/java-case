package com.jxau.wmx.dao;

import com.jxau.wmx.entity.OrderItem;

public interface IOrderItemDao {

	//增加订单项
	void saveOrderItem(OrderItem orderItem);
}
