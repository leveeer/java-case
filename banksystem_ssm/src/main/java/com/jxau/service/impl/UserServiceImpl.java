package com.jxau.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jxau.mapper.LogMapper;
import com.jxau.mapper.UserMapper;
import com.jxau.domain.Log;
import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "logMapper")
    private LogMapper logMapper;


    /**
     * 存款
     *
     * @param money
     */
    @Override
    public void deposit(BigDecimal money, int id) {

        User userInfo = userMapper.findUserById(id);
        Log log = new Log();

        log.setOpType("存款");
        log.setOpTime(new Date());
        log.setOpMoney(money);
        log.setBeforeMoney(userInfo.getMoney());
        log.setCurrentMoney(userInfo.getMoney().add(money));
        log.setUser(userInfo);

        userInfo.setMoney(userInfo.getMoney().add(money));
        userMapper.updateBalance(userInfo);
        logMapper.insertLog(log);
    }


    /**
     * 根据用户名查找用户
     *
     * @param getMoneyUser
     * @return
     */
    @Override
    public User findUserByUsername(String getMoneyUser) {

        User username = userMapper.findUserByUsername(getMoneyUser);
        System.out.println(username);
        return username;

    }

    /**
     * 转账
     *
     * @param getMoneyUser
     * @param transferMoney
     * @return
     */
    @Override
    public boolean transfer(int id, String getMoneyUser, BigDecimal transferMoney) {

        User userInfo = userMapper.findUserById(id);
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
            userMapper.updateBalance(userInfo);
            logMapper.insertLog(log);

            // 被转账人金额增加
            User getUser = userMapper.findUserByUsername(getMoneyUser);
            Log log1 = new Log();

            log1.setOpType("转入");
            log1.setOpTime(new Date());
            log1.setOpMoney(transferMoney);
            log1.setBeforeMoney(getUser.getMoney());
            log1.setCurrentMoney(getUser.getMoney().add(transferMoney));
            log1.setUser(getUser);
            logMapper.insertLog(log1);
            getUser.setMoney(getUser.getMoney().add(transferMoney));
            userMapper.updateBalance(getUser);
            return true;
        }
    }

    @Override
    public void updatePwdById(String id, String newPwd) {

        userMapper.updatePwdById(Integer.parseInt(id), newPwd);
    }

    @Override
    public User findUserByUid(String id) {
        return userMapper.findUserById(Integer.parseInt(id));
    }



    @Override
    public PageBean findRecordByPage(String id, int currentPage, int currentCount) {

        Page page = PageHelper.startPage(currentPage, currentCount);
        //当前页显示的数据
        List<Log> logs = logMapper.findAllLog(Integer.parseInt(id));

        //封装一个PageBean返回web层
        PageBean<Log> pageBean = new PageBean<Log>();
        //封装当前页
        pageBean.setCurrentPage(currentPage);
        //封装每页显示的条数
        pageBean.setCurrentCount(currentCount);
        //封装总记录数
        pageBean.setTotalCount(page.getTotal());
        //封装总页数
        pageBean.setTotalPage(page.getPages());
        //封装查询出来的数据
        pageBean.setList(logs);

        return pageBean;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAllUser();
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        return userMapper.findUserByUsernameAndPassword(user);
    }

    /**
     * 注册
     *
     * @param user
     */
    @Override
    public void userRegister(User user) {
        userMapper.register(user);
    }

    /**
     * 取款
     *
     * @param money
     * @return
     */
    @Override
    public boolean withdraw(int id, BigDecimal money) {
        User userInfo = userMapper.findUserById(id);
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
            userMapper.updateBalance(userInfo);
            logMapper.insertLog(log);
            return true;
        }
    }

}


