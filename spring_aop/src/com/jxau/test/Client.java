package com.jxau.test;

import com.jxau.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    /**
     * ApplicationContext与BeanFactory的区别
     *      ApplicationContext： 单例模式适用（容器一创建就创建bean,只创建一次） 多采用此方式
     *              他在创建核心容器时，创建对象采用的是立即加载的方式，也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象
     *      BeanFactory：        多例模式适用
     *              他在构建核心容器时，创建对象采用的策略是延迟加载的方式，也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象
     */

    private BeanFactory factory;

    @Before
    public void init(){
        factory = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testAop(){
        UserService userService = (UserService) factory.getBean("userService");
        userService.addUser("张三",30);
        System.out.println("====================");
        userService.delete(1);
    }
}
