package com.jxau.service.impl;

import com.jxau.dao.LogDao;
import com.jxau.dao.UserDao;
import com.jxau.domain.Log;
import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.util.PageBean;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Resource(name = "userDao")
    private UserDao  userDao;

    @Resource(name = "logDao")
    private LogDao  logDao;


    /**
     * 存款
     * @param money
     */
    @Override
    public void deposit(BigDecimal money, int id) {

            User userInfo = userDao.findUserById(id);
            Log log = new Log();

            log.setOpType("存款");
            log.setOpTime(new Date());
            log.setOpMoney(money);
            log.setBeforeMoney(userInfo.getMoney());
            log.setCurrentMoney(userInfo.getMoney().add(money));
            log.setUser(userInfo);

            userInfo.setMoney(userInfo.getMoney().add(money));
            userDao.updateBalance(userInfo);
            logDao.insertLog(log);
    }


    /**
     * 根据用户名查找用户
     * @param getMoneyUser
     * @return
     */
    @Override
    public User findUserByUsername(String getMoneyUser) {

        return  userDao.findUserByUsername(getMoneyUser);

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

                log.setOpType("转出");
                log.setOpTime(new Date());
                log.setOpMoney(transferMoney);
                log.setBeforeMoney(userInfo.getMoney());
                log.setCurrentMoney(userInfo.getMoney().subtract(transferMoney));
                log.setUser(userInfo);
                // 转账人金额减少
                userInfo.setMoney(userInfo.getMoney().subtract(transferMoney));
                userDao.updateBalance(userInfo);
                logDao.insertLog(log);

                // 被转账人金额增加
                User getUser = userDao.findUserByUsername(getMoneyUser);
                Log log1 = new Log();

                log1.setOpType("转入");
                log1.setOpTime(new Date());
                log1.setOpMoney(transferMoney);
                log1.setBeforeMoney(getUser.getMoney());
                log1.setCurrentMoney(getUser.getMoney().add(transferMoney));
                log1.setUser(getUser);
                logDao.insertLog(log1);
                getUser.setMoney(getUser.getMoney().add(transferMoney));
                userDao.updateBalance(getUser);
                return true;
            }
    }

    @Override
    public void updatePwdById(int id, String newPwd) {


            userDao.updatePwdById(id, newPwd);

    }

    @Override
    public User findUserByUid(String id) {

        return userDao.findUserById(Integer.parseInt(id));

    }

    @Override
    public PageBean findRecordByPage(String id, int currentPage, int currentCount) {

        //获取记录总条数
        Long totalCount = logDao.getTotalLogById(Integer.parseInt(id));
        //总页数 = (总条数 / 每页显示的条数) + 1
        Long totalPage;
        if (totalCount % currentCount == 0){
            totalPage = totalCount / currentCount;
        }else {
            totalPage = totalCount / currentCount + 1;
        }
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

        return userDao.findUserByUsernameAndPassword(user);
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
                log.setOpType("取款");
                log.setOpTime(new Date());
                log.setOpMoney(money);
                log.setBeforeMoney(userInfo.getMoney());
                log.setCurrentMoney(userInfo.getMoney().subtract(money));
                log.setUser(userInfo);
                userInfo.setMoney(userInfo.getMoney().subtract(money));
                userDao.updateBalance(userInfo);
                logDao.insertLog(log);
                return true;
            }
    }

}


