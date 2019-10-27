package com.jxau.test;

import com.jxau.domain.User;
import com.jxau.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAction {

    @Test
    public void testAdd(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = new User();
        user.setUsername("zhangsan");
        UserService userService = (UserService) ac.getBean("userService");
        userService.addUser(user);
    }

    @Test
    public void testDelete(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = new User();
        user.setUsername("zhangsan");
        user.setId(3);
        UserService userService = (UserService) ac.getBean("userService");
        userService.deleteUser(user);
    }
}
