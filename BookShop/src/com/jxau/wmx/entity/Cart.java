package com.jxau.wmx.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 购物车
 * @author Administrator
 *
 */

public class Cart implements Serializable {

	private Map<Integer,CartItem>map=new LinkedHashMap();
	private double total;//总计
	
	// Cart对象中有cartItems属性.获取所有商品信息
	public Collection<CartItem> getCartItems() {
			return map.values();
	}

	public double getTotal() {
		return total;
	}
	
	public Map<Integer, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}

	//增加购物车
	public void addCart(CartItem cartItem) {
		//获取新增加商品的id
		int pid=cartItem.getProduct().getPid();
		 //购物车中已经添加过该商品
		if(map.containsKey(pid)) {
		//获取购物车中原来的商品
		CartItem oldItem=map.get(pid);
		//设置数量
		oldItem.setCount(oldItem.getCount()+
				cartItem.getCount());
		}else {
			map.put(pid, cartItem);
		}
		// 设置总计的值
		total += cartItem.getSubTotal();
	}
	
	//清除购物车
	public void clearCart() {
		map.clear();
		total=0;
	}
	//删除某个商品
	public void removeCart(int pid) {
		CartItem cartItem=map.remove(pid);
		total-=cartItem.getSubTotal();
	}
	
}
