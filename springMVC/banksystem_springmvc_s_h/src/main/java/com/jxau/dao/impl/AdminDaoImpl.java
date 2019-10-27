package com.jxau.dao.impl;

import com.jxau.dao.AdminDao;
import com.jxau.domain.Admin;
import com.jxau.domain.User;
import org.hibernate.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

    @Override
    public Admin findAdminByNameAndPassword(String name, String password) {
        try {
            Query query = this.currentSession().createQuery("from Admin where username = :username and password = :password");
            query.setParameter("username", name);
            query.setParameter("password", password);
            Admin admin = (Admin) query.uniqueResult();
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void changeLocked(int id, int isLocked) {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        User user = hibernateTemplate.load(User.class, id);
        user.setIsLocked(isLocked);
        hibernateTemplate.save(user);
    }
}



