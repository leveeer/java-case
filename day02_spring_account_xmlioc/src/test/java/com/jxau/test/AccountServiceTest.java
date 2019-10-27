package com.jxau.test;

import com.jxau.domain.Account;
import com.jxau.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServiceTest {


    @Test
    public void findAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = ac.getBean("accountService",AccountService.class);
        List<Account> allAccount = as.findAllAccount();
        for (Account account : allAccount){
            System.out.println(account);
        }
    }

    @Test
    public void findOne(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = ac.getBean("accountService", AccountService.class);
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void saveAccount(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = ac.getBean("accountService", AccountService.class);
        Account account = new Account();
        account.setName("zlsg");
        account.setMoney(10000.00f);
        as.saveAccount(account);
    }

    @Test
    public void updateAccount(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = ac.getBean("accountService", AccountService.class);
        Account account = as.findAccountById(2);
        account.setMoney(123456f);
        as.updateAccount(account);

    }

    @Test
    public void deleteAccount(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = ac.getBean("accountService", AccountService.class);
        as.deletdAccount(3);
    }



}
