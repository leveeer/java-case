package com.jxau.dao.impl;

import com.jxau.dao.LogDao;
import com.jxau.domain.Log;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("logDao")
public class LogDaoImpl /*extends HibernateDaoSupport*/ implements LogDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void add(Log log) {
        sessionFactory.getCurrentSession().save(log);
    }
}
