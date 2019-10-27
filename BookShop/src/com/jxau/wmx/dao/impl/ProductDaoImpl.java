package com.jxau.wmx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.jxau.wmx.dao.IProductDao;
import com.jxau.wmx.entity.Product;
import com.jxau.wmx.util.C3P0Util;
import com.jxau.wmx.util.DBUtil;
/**
 * mysql数据库的操作的实现类
 * @author Administrator
 *
 */
public class ProductDaoImpl implements IProductDao {
  private static  Connection conn;
   static {
		conn=C3P0Util.getConn();
	
   }
	@Override
	public int insert(Product product) {
		
		return 0;
	}

	@Override
	public int update(Product product) {
		
		return 0;
	}

	@Override
	public int deleteById(int pid) {
		
		return 0;
	}

	@Override
	public List<Product> findAll() {
		
		return null;
	}

	@Override
	public Product findById(int pid) {
		String select_sql="select * from product where pid=?";
	   Product product=new Product();
		try {
		PreparedStatement	ps=conn.prepareStatement(select_sql);
		ps.setInt(1, pid);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			product.setPid(rs.getInt("pid"));
			product.setPname(rs.getString("pname"));
			product.setPrice(rs.getDouble("price"));
			product.setPnum(rs.getInt("pnum"));
			product.setImgurl(rs.getString("imgurl"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return product;
	}

	@Override
	public int findCount() {
		//Connection conn = C3P0Util.getDataSource().getConnection();
		String select_sql = "select count(*) from product";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(select_sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Product> findByPage(int page, int pagesize) {
		//Connection conn = DBUtil.getConn();
		List<Product> products = new ArrayList();
		String select_sql = "select *  from product limit ?,?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			 ps= conn.prepareStatement(select_sql);
			// 设置参数
			ps.setInt(1, (page - 1) * pagesize);
			ps.setInt(2, pagesize);
			// 执行查询
			rs= ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setPrice(rs.getDouble("price"));
				product.setPnum(rs.getInt("pnum"));
				product.setImgurl(rs.getString("imgurl"));
				products.add(product);}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, ps, rs);
		}
		return products;
	}
	

	@Override
	public List<Product> findByBookName(String pname) {  //根据书名找到商品,并将商品封装的Product类中
		List<Product> products = new ArrayList<Product>();
		String select_sql="select * from product where pname=?";
		try {
		PreparedStatement ps=conn.prepareStatement(select_sql);
		ps.setString(1, pname);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Product product = new Product();
			product.setPid(rs.getInt("pid"));
			product.setPname(rs.getString("pname"));
			product.setCategory(rs.getString("category"));
			product.setPrice(rs.getDouble("price"));
			product.setPnum(rs.getInt("pnum"));
			product.setImgurl(rs.getString("imgurl"));
			products.add(product);
		}
	} catch (SQLException e) {

		e.printStackTrace();
	}
		return products;
	}
	@Override
	public List<Product> findByBookPrice(double price1, double price2) {
		List<Product> products = new ArrayList<Product>();
		String select_sql="select * from product where price>=? and price<=?";		
		try {
		PreparedStatement ps=conn.prepareStatement(select_sql);
		ps.setDouble(1, price1);
		ps.setDouble(2, price2);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Product product = new Product();
			product.setPid(rs.getInt("pid"));
			product.setPname(rs.getString("pname"));
			product.setCategory(rs.getString("category"));
			product.setPrice(rs.getDouble("price"));
			product.setPnum(rs.getInt("pnum"));
			product.setImgurl(rs.getString("imgurl"));
			products.add(product);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return products;
	}

	@Override
	public List<Product> findByBookCategory(String categoryId) {
		List<Product> products = new ArrayList<Product>();
		ResultSet rs=null;
		String select_sql="select * from product where category=?";
		String select_all_sql="select * from product";
		try {
		PreparedStatement ps=conn.prepareStatement(select_sql);
		PreparedStatement ps0=conn.prepareStatement(select_all_sql);
		System.out.println(categoryId);
		if(categoryId.equals("全部")) {		 
			 rs=ps0.executeQuery();
		}else{
			ps.setString(1, categoryId);
			rs=ps.executeQuery();	
			}	
		while(rs.next()) {
			Product product = new Product();
			product.setPid(rs.getInt("pid"));
			product.setPname(rs.getString("pname"));
			product.setCategory(rs.getString("category"));
			product.setPrice(rs.getDouble("price"));
			product.setPnum(rs.getInt("pnum"));
			product.setImgurl(rs.getString("imgurl"));
			products.add(product);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return products;
	}
	

	/*
	 * 根据名称查找商品，未找到返回null
	 * @param bookName 商品名
	 * @return 包含该商品名的商品集合
	 */
 public List<Product> getProductBySearchName(int page,int pagesize,String keywords) {
		
		List<Product> products = new ArrayList(); 
		String select_sql = "select * from product limit ?,?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			 ps= conn.prepareStatement(select_sql);
		// 设置参数
			ps.setInt(1, (page - 1) * pagesize);
			ps.setInt(2, pagesize);
			// 执行查询
			rs= ps.executeQuery();
			while (rs.next()) {
				int pid=rs.getInt("pid");
			String pname=rs.getString("pname");
				double price=rs.getDouble("price");
				String category=rs.getString("category");
				int pnum=rs.getInt("pnum");
				String imgurl=rs.getString("imgurl");
				String description=rs.getString("description");
				String pinfo=pid+pname+category+description;
				if(pinfo.contains(keywords)) {
					Product product = new Product();
					product.setPid(pid);
					product.setPname(pname);
					product.setPrice(price);
					product.setCategory(category);
					product.setPnum(pnum);
					product.setImgurl(imgurl);
					product.setDesc(description);
					products.add(product);
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn, ps, rs);
		}
    System.out.println(products);
		return products;
	}

	/*
	 * 根据商品种类获取商品
	 * @param category 商品种类
	 * @return Set 该种类商品集合
	 */
	public Set<Product> getProductByCategory(String category) {
		Set<Product> set = null;
		
		try {
			String sql = "SELECT * FROM product";
			//Connection connection = DataSourceUtils.getConnection();
			Statement statement = conn.createStatement();

			if (!category.equals("全部商品")) {
				sql += " where category='" + category + "'";
			}
			set = new LinkedHashSet<Product>();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				Product product = new Product();
				product.setPid(resultSet.getInt("pid"));
				product.setPname(resultSet.getString("pname"));
				product.setPrice(resultSet.getDouble("price"));
				product.setCategory(resultSet.getString("category"));
				product.setPnum(resultSet.getInt("pnum"));
				product.setImgurl(resultSet.getString("imgurl"));
				product.setDesc(resultSet.getString("description"));
				set.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return set;
	}

}
