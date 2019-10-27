package com.jxau.dao.impl;

import com.jxau.dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {
    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
