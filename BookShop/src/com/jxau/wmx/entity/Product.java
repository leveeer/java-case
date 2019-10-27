package com.jxau.wmx.entity;

import java.io.Serializable;

/**
 * 属性和数据库表的字段是一一对应的
 * @author Administrator
 *
 */
public class Product implements Serializable {
	private int pid;
	private String pname;
	private double price;
	private String category;
	private int pnum;
	private String imgurl;
	private String desc;
	//重写了带参的构造函数
	
	//把默认的 构造函数重写
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		
		return pid+","+pname;
	}
	
}
