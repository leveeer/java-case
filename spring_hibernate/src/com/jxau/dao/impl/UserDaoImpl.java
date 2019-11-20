package com.jxau.dao.impl;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import com.jxau.util.HibernateUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Override
    public void add(User user) {
        /*HibernateUtils.getCurrentSession().save(user);*/
        this.getHibernateTemplate().save(user);
    }

    @Override
    public void delete(User user) {
        getHibernateTemplate().delete(user);


    }
}
