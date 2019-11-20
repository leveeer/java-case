package com.jxau.service.impl;

import com.jxau.dao.AccountDao;
import com.jxau.dao.impl.AccountDaoImpl;
import com.jxau.service.AccountService;

public class AccountServiceImpl implements AccountService {
    AccountDao ad = new AccountDaoImpl();
    public void saveAccount() {
        ad.saveAccount();
    }
}
