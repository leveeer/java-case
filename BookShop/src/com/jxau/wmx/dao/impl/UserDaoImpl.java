package com.jxau.wmx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jxau.wmx.dao.IUserDao;
import com.jxau.wmx.entity.User;
import com.jxau.wmx.util.C3P0Util;


public class UserDaoImpl implements IUserDao {

	@Override
	public User login(String name, String pwd) {
     Connection conn=C3P0Util.getConn();
     String select_sql="select * from userinfo where username=? and pwd=?";
     PreparedStatement ps=null;
     ResultSet rs=null;
     User user=null;
	try {
		ps = conn.prepareStatement(select_sql);
		ps.setString(1, name);
	    ps.setString(2, pwd);
	    rs=ps.executeQuery();
	    while(rs.next()) {
	    if(rs.getString("username").equals(name)&&rs.getString("pwd").equals(pwd)) {
	    	user=new User();
	    	user.setUid(rs.getInt("uid"));
	    	user.setUsername(name);
	    	user.setPwd(pwd);
	    	user.setAddress(rs.getString("address"));
	    	user.setName(rs.getString("name"));
	    	user.setPhone(rs.getString("phone"));
	    	user.setEmail(rs.getString("email"));
	    }
	   }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	return user;
	}

	@Override
	public int register(User user) {
		//1、获取连接对象
		Connection conn=C3P0Util.getConn();
		//2、执行sql语句块
		String insert_sql="insert into userinfo(username,pwd,email) values(?,?,?)";
		int result=0;
		try {
			PreparedStatement ps=conn.prepareStatement(insert_sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getEmail());
			result=ps.executeUpdate();
		    //return result;
	   }catch (Exception e) {
		
	}
		 return result;
}

	@Override
	public boolean findByName(String name) {
		String select_sql="select * from userinfo where username=?";
		Connection conn=C3P0Util.getConn();
		try {
			PreparedStatement ps=conn.prepareStatement(select_sql);
		    ps.setString(1, name);
		    ResultSet rs=ps.executeQuery();
		    if(rs.next()) {
		    	return true;//查找到用户
		    }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
}	
