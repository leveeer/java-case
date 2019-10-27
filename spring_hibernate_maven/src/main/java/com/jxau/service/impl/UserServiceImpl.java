package com.jxau.service.impl;

import com.jxau.dao.LogDao;
import com.jxau.dao.UserDao;
import com.jxau.domain.Log;
import com.jxau.domain.User;
import com.jxau.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userService")
public class UserServiceImpl /*extends HibernateDaoSupport*/ implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LogDao logDao;
    /*@Autowired
    private SessionFactory sessionFactory;*/

   /* public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }*/


    public void addUser(User user) {

        userDao.addUser(user);
        Log log = new Log();
        log.setType("SecurityLog");
        log.setDetail("add User : " + user.getUsername());
        log.setTime(new Date());
        logDao.add(log);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
        Log log = new Log();
        log.setType("SecurityLog");
        log.setDetail("delete User : " + user.getId());
        log.setTime(new Date());
        logDao.add(log);
    }
}
