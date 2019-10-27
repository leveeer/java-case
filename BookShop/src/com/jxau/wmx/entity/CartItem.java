package com.jxau.wmx.entity;

import java.io.Serializable;

/**
 * 
 * 购物车单个商品信息
 *
 */
public class CartItem implements Serializable{
	
	private Product product;//购物车商品信息
	private int count;//单个商品购买的数量
	private double subTotal;//购买某种商品小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//自动计算 
	public double getSubTotal() {
		return count*product.getPrice();
	}
	

}
