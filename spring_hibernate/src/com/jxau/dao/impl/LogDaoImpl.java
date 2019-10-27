package com.jxau.dao.impl;

import com.jxau.dao.LogDao;
import com.jxau.domain.Log;
import com.jxau.util.HibernateUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class LogDaoImpl extends HibernateDaoSupport implements LogDao {
    @Override
    public void add(Log log) {
        /*HibernateUtils.getCurrentSession().save(log);*/
        this.getHibernateTemplate().save(log);
    }
}
