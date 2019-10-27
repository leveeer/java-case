package com.jxau.ui;

import com.jxau.dao.AccountDao;
import com.jxau.service.AccountService;
import com.jxau.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        //获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //根据id获取bean对象
        AccountService as = (AccountService)ac.getBean("accountService");
        System.out.println(as);

        AccountDao ad = ac.getBean("accountDao",AccountDao.class);
        System.out.println(ad);

        as.saveAccount();


    }
}
