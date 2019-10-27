package com.jxau.wmx.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;

import com.jxau.wmx.dao.IProductDao;
import com.jxau.wmx.dao.impl.ProductDaoImpl;
import com.jxau.wmx.entity.Product;
import com.jxau.wmx.factory.FactoryBean;
import com.jxau.wmx.util.C3P0Util;

public class ProductTest {

	/**
	 * 单元测试：测试类中的方法是否正确
	 */
	@Test
	public void test() {
	try {
		IProductDao productDao =FactoryBean.
			getInstance("productimpl", IProductDao.class);
	   int count=productDao.findCount();
	   System.out.println("总记录数："+count);
	   List<Product>products=productDao.findByPage(1, 2);
	   System.out.println("查询第一页的数据："+products);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	/*@Test
	public void testc3p0() {
		DataSource ds=C3P0Util.getDataSource();
		try {
			Connection conn=ds.getConnection();
			System.out.println(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
