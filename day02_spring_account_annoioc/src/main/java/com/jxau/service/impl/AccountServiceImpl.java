package com.jxau.service.impl;

import com.jxau.dao.AccountDao;
import com.jxau.domain.Account;
import com.jxau.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;



    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    public void saveAccount(Account account) {

        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {

        accountDao.updateAccount(account);
    }

    public void deletdAccount(Integer id) {

        accountDao.deletdAccount(id);
    }
}
