package com.jxau.bank.manager;

import com.jxau.bank.model.MoneyBean;
import com.jxau.bank.util.AccountOverDrawnException;
import com.jxau.bank.util.InvalidDepositException;

import java.io.IOException;

public interface ManagerInterface {

    /**
     * 注册功能
     *
     * @param name
     * @param password
     * @throws IOException
     */
    public abstract void register(String name, String password) throws IOException;

    /**
     * 登录功能
     *
     * @param loginName
     * @return
     * @throws IOException
     */
    public abstract MoneyBean login(String loginName) throws IOException;


    /**
     * 查询余额功能
     */
    public abstract double inquiry() throws IOException;

    /**
     * 存款功能
     *
     * @param money
     * @throws InvalidDepositException
     */
    public abstract double deposit(double money) throws InvalidDepositException, IOException;

    /**
     * 取款功能
     *
     * @param money
     * @throws AccountOverDrawnException
     */
    public abstract double withdrawals(double money) throws AccountOverDrawnException, IOException;

    /**
     * 转账功能
     * @param name
     * @param money
     * @return
     */
    public abstract void transfer(String name, double money) throws IOException;

    /**
     * 退出功能
     */
    public abstract void exitSystem();
}
