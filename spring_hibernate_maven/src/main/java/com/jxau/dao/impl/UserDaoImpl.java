package com.jxau.dao.impl;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        getHibernateTemplate().save(user);
    }

    public void deleteUser(User user) {
        /*User load = getHibernateTemplate().load(User.class, user.getId());
        getHibernateTemplate().delete(load);*/
        User load = sessionFactory.getCurrentSession().load(User.class, user.getId());
        sessionFactory.getCurrentSession().delete(load);
    }
}
