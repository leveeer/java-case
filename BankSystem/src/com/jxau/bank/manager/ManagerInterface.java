package com.jxau.bank.manager;

import com.jxau.bank.model.OperateBean;
import com.jxau.bank.model.UserBean;
import com.jxau.bank.util.AccountOverDrawnException;
import com.jxau.bank.util.AccountOverTransferException;
import com.jxau.bank.util.InvalidDepositException;
import com.jxau.bank.util.InvalidWithdrawalsException;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public interface ManagerInterface {
    /**
     * 注册功能
     * @param registerName
     * @param password
     * @return
     */
    public abstract boolean register(String registerName, String password) throws IOException;

    /**
     * 登录功能
     * @param loginName
     * @param loginPassword
     * @return
     */
    public abstract boolean login(String loginName, String loginPassword) throws IOException;

    /**
     * 查询余额功能
     * @return
     */
    public abstract double inquiry() throws IOException;

    /**
     * 存款功能
     * @param addMoney
     */
    public abstract void deposit(double addMoney) throws InvalidDepositException;

    /**
     * 取款功能
     * @param getMoney
     */
    public abstract void withdrawals(double getMoney) throws AccountOverDrawnException, InvalidWithdrawalsException, IOException;

    /**
     * 退出功能
     */
    public abstract void exitSystem() throws IOException;

    /**
     * 转账功能
     * @param getMoneyUser
     * @param transferMoney
     * @return
     */
    public abstract boolean transfer(String getMoneyUser, double transferMoney) throws IOException, AccountOverTransferException;



    public abstract Vector<OperateBean> findRecordByUsername(String username);

    public abstract UserBean findUserByName(String username);


    public abstract List<UserBean> findAll();

    void blockedUserById(int id,int isBlocked);
}


