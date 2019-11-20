package com.jxau.service.impl;

import com.jxau.dao.AccountDao;
import com.jxau.domain.Account;
import com.jxau.service.AccountService;
import com.jxau.utils.TransactionManager;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        try {
            //开启事务
            txManager.beginTransaction();
            //执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //提交事务
            txManager.commit();
            //返回结果
            return accounts;
        }catch (Exception e){
            //回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            txManager.release();
        }
    }

    public Account findAccountById(Integer id) {
        try {
            //开启事务
            txManager.beginTransaction();
            //执行操作
            Account account = accountDao.findAccountById(id);
            //提交事务
            txManager.commit();
            //返回结果
            return account;
        }catch (Exception e){
            //回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            txManager.release();
        }
    }

    public void saveAccount(Account account) {
        try {
            //开启事务
            txManager.beginTransaction();
            //执行操作
            accountDao.saveAccount(account);
            //提交事务
            txManager.commit();
        }catch (Exception e){
            //回滚事务
            txManager.rollback();
        }finally {
            //释放连接
            txManager.release();
        }
    }

    public void updateAccount(Account account) {
        try {
            //开启事务
            txManager.beginTransaction();
            //执行操作
            accountDao.updateAccount(account);
            //提交事务
            txManager.commit();

        }catch (Exception e){
            //回滚事务
            txManager.rollback();
        }finally {
            //释放连接
            txManager.release();
        }
    }

    public void deletdAccount(Integer id) {
        try {
            //开启事务
            txManager.beginTransaction();
            //执行操作
            accountDao.deletdAccount(id);
            //提交事务
            txManager.commit();

        }catch (Exception e){
            //回滚事务
            txManager.rollback();
        }finally {
            //释放连接
            txManager.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {

        try {
            //开启事务
            txManager.beginTransaction();
            //执行操作
            //根据名称查询转出用户
            Account source = accountDao.findAccountByName(sourceName);

            //根据名称查询转入用户
            Account target = accountDao.findAccountByName(targetName);

            //转出用户减钱
            source.setMoney(source.getMoney() - money);

            //转入用户加钱
            target.setMoney(target.getMoney() + money);

            //更新转出用户
            accountDao.updateAccount(source);
            int i = 1/0;
            //更新转入用户
            accountDao.updateAccount(target);
            //提交事务
            txManager.commit();
        }catch (Exception e){
            //回滚事务
            txManager.rollback();
            e.printStackTrace();
        }finally {
            //释放连接
            txManager.release();
        }

    }
}
