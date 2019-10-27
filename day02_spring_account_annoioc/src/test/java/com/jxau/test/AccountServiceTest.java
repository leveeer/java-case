package com.jxau.test;

import config.SpringConfiguration;
import com.jxau.domain.Account;
import com.jxau.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * spring整合junit的配置
 * 1、导入spring整合junit的jar包（坐标）
 * 2、使用junit提供的一个注解把原有的main方法替换，替换成spring提供的
 *          @Runwith:
 * 3、告知spring的运行器，spring的ioc创建是基于xml还是注解，并说明位置
 *          @ContextConfiguration
 *                      locations；指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                      classes:指定注解类所在位置
 *    当我们使用spring 5.x版本的时候，要求junit的jar包必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {


    @Autowired
    private AccountService as;

    /*@Before
    public void init(){
        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        as = ac.getBean("accountService",AccountService.class);
    }*/

    @Test
    public void findAll(){
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        /*ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AccountService as = ac.getBean("accountService",AccountService.class);*/
        List<Account> allAccount = as.findAllAccount();
        for (Account account : allAccount){
            System.out.println(account);
        }
    }

    @Test
    public void findOne(){
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        AccountService as = ac.getBean("accountService", AccountService.class);
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void saveAccount(){
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        AccountService as = ac.getBean("accountService", AccountService.class);
        Account account = new Account();
        account.setName("zlsg");
        account.setMoney(10000.00f);
        as.saveAccount(account);
    }

    @Test
    public void updateAccount(){
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        AccountService as = ac.getBean("accountService", AccountService.class);
        Account account = as.findAccountById(2);
        account.setMoney(123456f);
        as.updateAccount(account);

    }

    @Test
    public void deleteAccount(){
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        AccountService as = ac.getBean("accountService", AccountService.class);
        as.deletdAccount(3);
    }



}
