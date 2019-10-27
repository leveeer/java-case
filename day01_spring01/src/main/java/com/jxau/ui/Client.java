package com.jxau.ui;

import com.jxau.dao.AccountDao;
import com.jxau.service.AccountService;
import com.jxau.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {
    /**
     * 获取spring的Ioc核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类
     *      ClassPathXmlApplicationContext:他可以加载类路径下的配置文件，要求配置文件必须在类路径下，不在的话加载不了(常用)
     *      FileSystemXmlApplicationContext:它可以加载磁盘任意路径下的配置文件（必须有访问权限）
     *
     *      AnnotationConfigApplicationContext:他是用于读取注解创建容器的
     *
     * 核心容器的两个接口引发出的问题
     * ApplicationContext:  单例对象适用      实际开发中常采用此接口来定义容器对象
     *          他在构建核心容器时，创建对象采取的策略是采用立即加载的方式，也就是说，一读取完配置文件马上就创建配置文件中配置的对象(反射的方式)
     * BeanFactory:    多例对象适用
     *          他在构建核心容器时，创建对象采取的策略是延迟加载的方式，也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象
     * @param args
     */
    public static void main(String[] args) {
        //获取核心容器对象
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new FileSystemXmlApplicationContext("C:\\Users\\xie\\Desktop\\bean.xml");
        //根据id获取bean对象
        AccountService as = (AccountService)ac.getBean("accountService");
        AccountDao ad = ac.getBean("accountDao",AccountDao.class);

        System.out.println(as);
        System.out.println(ad);
    }
}
