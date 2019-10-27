package com.jxau.dao.impl;

import com.jxau.dao.LinkManDao;
import com.jxau.domain.LinkMan;
import com.jxau.utils.HibernateUtils;
import org.hibernate.Session;


public class LinkManDaoImpl implements LinkManDao {

	public void save(LinkMan lm) {
		//1 获得session
		Session session = HibernateUtils.getCurrentSession();
		session.save(lm);
	}

}
