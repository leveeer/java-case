package com.jxau.bank.dao;

import com.jxau.bank.model.UserBean;

import java.io.IOException;
import java.util.List;

public interface BankDaoInterface {

    /**
     * 注册功能
     * @param registerName
     * @param password
     * @throws IOException
     */
    public abstract void register(String registerName, String password) throws IOException;

    /**
     * 登录功能
     * @param loginName
     * @param loginPassword
     * @return
     * @throws IOException
     */
    public abstract UserBean login(String loginName, String loginPassword) throws IOException;


    /**
     * 将用户余额存入properties中
     * @param money
     * @param id
     * @throws IOException
     */
    public abstract void addBank(double money, int id) throws IOException;

    /**
     * 转账功能
     * @param getMoneyUser
     * @param currentMoney
     */
    public abstract void transfer(String getMoneyUser, double currentMoney) throws IOException;

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    public abstract UserBean findUserByName(String username);

    List<UserBean> findAll();

    void blockedUserById(int id,int isBlocked);
}
