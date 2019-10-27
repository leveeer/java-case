package com.jxau.service.impl;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import com.jxau.dao.LogDao;
import com.jxau.dao.UserDao;
import com.jxau.domain.Log;
import com.jxau.domain.User;
import com.jxau.factory.BeanFactory;
import com.jxau.service.UserService;

public class UserServiceImpl implements UserService {

    // private User userInfo = null;
    private static UserServiceImpl instance;
    private UserDao                userDao = (UserDao) BeanFactory.getBean("userDao");
    private LogDao                 logDao  = (LogDao) BeanFactory.getBean("logDao");

    private UserServiceImpl() {}

    /**
     * 存款
     * @param money
     */
    @Override
    public void deposit(BigDecimal money, int id) {
        User userInfo = userDao.findUserById(id);
        Log  log      = new Log();

        log.setUid(userInfo.getId());
        log.setUsername(userInfo.getUsername());
        log.setOpType("存款");
        log.setOpTime(new Timestamp(new Date().getTime()));
        log.setOpMoney(money);
        log.setBeforeMoney(userInfo.getMoney());
        log.setCurrentMoney(userInfo.getMoney().add(money));
        userDao.addBalance(id, userInfo.getMoney().add(money));
        logDao.insertLog(log);
    }

    /**
     * 查询所有记录
     * @return
     */
    @Override
    public List<Log> findAllRecord(String id) {
        return logDao.findRecordById(Integer.parseInt(id));
    }

    /**
     * 根据用户名查找用户
     * @param getMoneyUser
     * @return
     */
    @Override
    public User findUserByUsername(String getMoneyUser) {

        // System.out.println(getMoneyUser);
        User user = userDao.findUserByUsername(getMoneyUser);

        // System.out.println(user);
        return user;
    }

    /**
     * 转账
     * @param getMoneyUser
     * @param transferMoney
     * @return
     */
    @Override
    public boolean transfer(int id, String getMoneyUser, BigDecimal transferMoney) {
        User userInfo = userDao.findUserById(id);

        if (userInfo.getMoney().compareTo(transferMoney) == -1) {
            return false;
        } else {
            Log log = new Log();

            log.setUid(userInfo.getId());
            log.setUsername(userInfo.getUsername());
            log.setOpType("转出");
            log.setOpTime(new Timestamp(new Date().getTime()));
            log.setOpMoney(transferMoney);
            log.setBeforeMoney(userInfo.getMoney());
            log.setCurrentMoney(userInfo.getMoney().subtract(transferMoney));

            // 转账人金额减少
            userDao.addBalance(id, userInfo.getMoney().subtract(transferMoney));
            logDao.insertLog(log);

            // 被转账人金额增加
            User getUser = userDao.findUserByUsername(getMoneyUser);
            Log  log1    = new Log();

            log1.setUid(getUser.getId());
            log1.setUsername(getUser.getUsername());
            log1.setOpType("转入");
            log1.setOpTime(new Timestamp(new Date().getTime()));
            log1.setOpMoney(transferMoney);
            log1.setBeforeMoney(getUser.getMoney());
            getUser.setMoney(getUser.getMoney().add(transferMoney));
            log1.setCurrentMoney(getUser.getMoney());
            logDao.insertLog(log1);
            userDao.addBalance(getUser.getId(), getUser.getMoney());

            return true;
        }
    }

    @Override
    public void updatePwdById(int id, String newPwd) {
        userDao.updatePwdById(id, newPwd);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        try {
            User userInfo = userDao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());

            return userInfo;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void userRegister(User user) {
        userDao.register(user);
    }

    /**
     * 取款
     * @param money
     * @return
     */
    @Override
    public boolean withdraw(int id, BigDecimal money) {
        User userInfo = userDao.findUserById(id);

        if (userInfo.getMoney().compareTo(money) == -1) {
            return false;
        } else {
            Log log = new Log();

            log.setUid(userInfo.getId());
            log.setUsername(userInfo.getUsername());
            log.setOpType("取款");
            log.setOpTime(new Timestamp(new Date().getTime()));
            log.setOpMoney(money);
            log.setBeforeMoney(userInfo.getMoney());
            log.setCurrentMoney(userInfo.getMoney().subtract(money));
            userDao.addBalance(id, userInfo.getMoney().subtract(money));
            logDao.insertLog(log);

            return true;
        }
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }

        return instance;
    }
}


