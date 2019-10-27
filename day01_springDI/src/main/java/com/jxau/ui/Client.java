package com.jxau.ui;

import com.jxau.service.AccountService;
import com.jxau.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //根据id获取bean对象
        /*AccountService as = (AccountService)ac.getBean("accountService");

        as.saveAccount();*/
       /* AccountService as2 = (AccountService)ac.getBean("accountService2");

        as2.saveAccount();*/
        AccountService as3 = (AccountService)ac.getBean("accountService3");

        as3.saveAccount();

    }
}
