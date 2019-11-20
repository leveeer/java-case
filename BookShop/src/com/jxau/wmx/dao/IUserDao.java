package com.jxau.wmx.dao;

import com.jxau.wmx.entity.User;

public interface IUserDao {

	public User login(String name, String pwd);
	
	public int register(User user);
	//ʵ��ajaxУ���û����Ƿ����
	public boolean findByName(String name);
	
}
