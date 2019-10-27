package com.jxau.gmall.service.impl;

import com.jxau.gmall.bean.UserAddress;
import com.jxau.gmall.service.OrderService;
import com.jxau.gmall.service.UserService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    UserService userService;
    public void initOrder(String userId) {
        //查询用户的收货地址
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        System.out.println(addressList);
    }
}
