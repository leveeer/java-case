package com.jxau.service.impl;


import com.jxau.dao.CustomerDao;
import com.jxau.dao.LinkManDao;
import com.jxau.dao.impl.CustomerDaoImpl;
import com.jxau.dao.impl.LinkManDaoImpl;
import com.jxau.domain.Customer;
import com.jxau.domain.LinkMan;
import com.jxau.service.LinkManService;
import com.jxau.utils.HibernateUtils;

public class LinkManServiceImpl implements LinkManService {

	private CustomerDao cd =new CustomerDaoImpl();
	private LinkManDao lmd = new LinkManDaoImpl();
	public void save(LinkMan lm) {
		//打开事务
		HibernateUtils.getCurrentSession().beginTransaction();
		
		try {
			//1 根据客户id获得客户对象
			Long cust_id = lm.getCust_id();
			Customer c = cd.getById(cust_id);
			//2 将客户放入LinkMan中表达关系
			lm.setCustomer(c);
			//3 保存LinkMan
			lmd.save(lm);
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			HibernateUtils.getCurrentSession().getTransaction().rollback();
		}
		//提交事务
		HibernateUtils.getCurrentSession().getTransaction().commit();
		
	}

}
