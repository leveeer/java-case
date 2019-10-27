package com.jxau.service;

import java.util.List;

import com.jxau.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;


public interface CustomerService {
	//保存客户
	void save(Customer c);
	//获得所有客户
	List<Customer> getAll();
	//根据条件查询所有客户
	List<Customer> getAll(DetachedCriteria dc);

}
