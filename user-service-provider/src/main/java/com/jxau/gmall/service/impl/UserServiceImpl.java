package com.jxau.gmall.service.impl;

import java.util.Arrays;
import java.util.List;
import com.jxau.gmall.bean.UserAddress;
import com.jxau.gmall.service.UserService;

/**
 * 1、将服务提供者注册到注册中心
 * 		1.1 导入dubbo依赖
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 * 3、
 */
public class UserServiceImpl implements UserService {
	public List<UserAddress> getUserAddressList(String userId) {
		System.out.println("UserServiceImpl.....old...");

		UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
		return Arrays.asList(address1,address2);
	}

}
