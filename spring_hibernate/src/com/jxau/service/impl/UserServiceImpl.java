package com.jxau.service.impl;

import com.jxau.dao.LogDao;
import com.jxau.dao.UserDao;
import com.jxau.dao.impl.LogDaoImpl;
import com.jxau.dao.impl.UserDaoImpl;
import com.jxau.domain.Log;
import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.Date;

public class UserServiceImpl extends HibernateDaoSupport implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private LogDao logDao = new LogDaoImpl();

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public void add(User user) {

        userDao.add(user);
        Log log = new Log();
        log.setType("SecurityLog");
        log.setDetail("input system add info:" + user.getUsername());
        log.setTime(new Date());
        logDao.add(log);

        /*Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            userDao.add(user);
            Log log = new Log();
            log.setType("SecurityLog");
            log.setDetail("input system add info:" + user.getUsername());
            log.setTime(new Date());
            logDao.add(log);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }*/

    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
        Log log = new Log();
        log.setType("SecurityLog");
        log.setDetail("input system delete info:" + user.getUsername());
        log.setTime(new Date());
        logDao.add(log);
    }
}
