package com.jxau.service;

import com.jxau.domain.Log;
import com.jxau.domain.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    void userRegister(User user);

    User userLogin(User user);

    void deposit(BigDecimal money, int id);

    boolean withdraw(int id, BigDecimal money);

    User findUserByUsername(String getMoneyUser);

    boolean transfer(int id, String getMoneyUser, BigDecimal transferMoney);

    List<Log> findAllRecord(String id);

    void updatePwdById(int id, String newPwd);
}
