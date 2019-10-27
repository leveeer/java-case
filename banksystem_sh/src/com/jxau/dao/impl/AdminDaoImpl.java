package com.jxau.dao.impl;

import com.jxau.dao.AdminDao;
import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.util.HibernateFilter;
import com.jxau.util.HibernateUtils;
import org.hibernate.Query;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin findAdminByNameAndPassword(String name, String password) {
        try {
            Query query = HibernateFilter.getSession().createQuery("from Admin where username = :username and password = :password");
            query.setString("username", name);
            query.setString("password", password);
            Admin admin = (Admin) query.uniqueResult();
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void changeLocked(int id, int isLocked) {
        User user = HibernateFilter.getSession().load(User.class, id);
        user.setIsLocked(isLocked);
        HibernateFilter.getSession().save(user);
    }
}



