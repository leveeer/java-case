package com.jxau.dao;

import com.jxau.domain.Account;
import com.jxau.domain.AccountUser;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    List<AccountUser> findAllAccount();
}
