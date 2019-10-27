package com.jxau.service.impl;

import com.jxau.dao.LogDao;
import com.jxau.dao.UserDao;
import com.jxau.domain.Log;
import com.jxau.domain.User;
import com.jxau.factory.BeanFactory;
import com.jxau.service.UserService;
import com.jxau.util.HibernateFilter;
import com.jxau.util.HibernateUtils;
import com.jxau.util.PageBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

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
        Transaction transaction = null;
        try {
            Session session = HibernateFilter.getSession();
            transaction = session.getTransaction();
            transaction.begin();
            User userInfo = userDao.findUserById(id);
            Log log = new Log();

            log.setOpType("存款");
            log.setOpTime(new Date());
            log.setOpMoney(money);
            log.setBeforeMoney(userInfo.getMoney());
            log.setCurrentMoney(userInfo.getMoney().add(money));
            log.setUser(userInfo);

            userInfo.setMoney(userInfo.getMoney().add(money));
            userDao.addBalance(userInfo);
            logDao.insertLog(log);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }


    /**
     * 根据用户名查找用户
     * @param getMoneyUser
     * @return
     */
    @Override
    public User findUserByUsername(String getMoneyUser) {
        Transaction transaction = null;
        try {
            Session session = HibernateFilter.getSession();
            transaction = session.getTransaction();
            transaction.begin();
            User user = userDao.findUserByUsername(getMoneyUser);
            transaction.commit();
            return user;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转账
     * @param getMoneyUser
     * @param transferMoney
     * @return
     */
    @Override
    public boolean transfer(int id, String getMoneyUser, BigDecimal transferMoney) {
        Transaction transaction = null;
        try {
            Session session = HibernateFilter.getSession();
            transaction = session.getTransaction();
            transaction.begin();
            User userInfo = userDao.findUserById(id);

            if (userInfo.getMoney().compareTo(transferMoney) == -1) {
                return false;
            } else {
                Log log = new Log();

                log.setOpType("转出");
                log.setOpTime(new Date());
                log.setOpMoney(transferMoney);
                log.setBeforeMoney(userInfo.getMoney());
                log.setCurrentMoney(userInfo.getMoney().subtract(transferMoney));
                log.setUser(userInfo);
                // 转账人金额减少
                userInfo.setMoney(userInfo.getMoney().subtract(transferMoney));
                userDao.addBalance(userInfo);
                logDao.insertLog(log);

                // 被转账人金额增加
                User getUser = userDao.findUserByUsername(getMoneyUser);
                Log  log1    = new Log();

                log1.setOpType("转入");
                log1.setOpTime(new Date());
                log1.setOpMoney(transferMoney);
                log1.setBeforeMoney(getUser.getMoney());
                log1.setCurrentMoney(getUser.getMoney().add(transferMoney));
                log1.setUser(getUser);
                logDao.insertLog(log1);
                getUser.setMoney(getUser.getMoney().add(transferMoney));
                userDao.addBalance(getUser);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updatePwdById(int id, String newPwd) {

        Transaction transaction = null;
        try {
            Session session = HibernateFilter.getSession();
            transaction = session.getTransaction();
            transaction.begin();
            userDao.updatePwdById(id, newPwd);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByUid(String id) {
        Transaction transaction = null;
        try {
            Session session = HibernateFilter.getSession();
            transaction = session.beginTransaction();
            User user = userDao.findUserById(Integer.parseInt(id));
            transaction.commit();
            return user;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageBean findRecordByPage(String id, int currentPage, int currentCount) {

        //获取记录总条数
        Long totalCount = logDao.getTotalLogById(Integer.parseInt(id));
        //总页数 = (总条数 / 每页显示的条数) + 1
        Long totalPage = (totalCount / currentCount) + 1;
        //开始查询的索引 = （当前页码 - 1） * 每页显示的条数
        int index = (currentPage - 1) * currentCount;
        //当前页显示的数据
        List<Log> logs = logDao.findLogByPage(Integer.parseInt(id), index, currentCount);

        //封装一个PageBean返回web层
        PageBean<Log> pageBean = new PageBean<Log>();
        //封装当前页
        pageBean.setCurrentPage(currentPage);
        //封装每页显示的条数
        pageBean.setCurrentCount(currentCount);
        //封装总记录数
        pageBean.setTotalCount(totalCount);
        //封装总页数
        pageBean.setTotalPage(totalPage);
        //封装查询出来的数据
        pageBean.setList(logs);

        return pageBean;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {

        Transaction transaction = null;
        try {
            Session session = HibernateFilter.getSession();
            transaction = session.beginTransaction();
            User userLogin = userDao.findUserByUsernameAndPassword(user);
            transaction.commit();
            return userLogin;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void userRegister(User user) {


        Transaction transaction = null;
        try {
            Session session = HibernateFilter.getSession();
            transaction = session.beginTransaction();
            userDao.register(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * 取款
     * @param money
     * @return
     */
    @Override
    public boolean withdraw(int id, BigDecimal money) {
        Transaction transaction = null;
        try {

            Session session = HibernateFilter.getSession();
            transaction = session.getTransaction();
            transaction.begin();
            User userInfo = userDao.findUserById(id);
            if (userInfo.getMoney().compareTo(money) == -1) {
                return false;
            } else {
                Log log = new Log();
                log.setOpType("取款");
                log.setOpTime(new Date());
                log.setOpMoney(money);
                log.setBeforeMoney(userInfo.getMoney());
                log.setCurrentMoney(userInfo.getMoney().subtract(money));
                log.setUser(userInfo);
                userInfo.setMoney(userInfo.getMoney().subtract(money));
                userDao.addBalance(userInfo);
                logDao.insertLog(log);
                transaction.commit();
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }

    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }

        return instance;
    }
}


