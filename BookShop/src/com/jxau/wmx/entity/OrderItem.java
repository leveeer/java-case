package com.jxau.wmx.entity;
/**
 * 订单表和商品表的关系中的中间表
 * @author Administrator
 *
 */
public class OrderItem {

	private String oid;
	private Product product;//页面需要的商品表的数据，修改为保存商品表，而不是pid
	private int buynum;//购买数量
	private double subTotal;//小计
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	
}
