package com.jxau.test;

import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAction {
    //private UserService userService = new UserServiceImpl();
    @Test
    public void testAction(){
        User user = new User();
        user.setUsername("张三");
       // userService.add(user);
    }

    @Test
    public void testSpringHbm_add(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-*.xml");
        UserService userService = (UserService) ac.getBean("userService");
        User user = new User();
        user.setUsername("lisi");
        userService.add(user);
    }

    @Test
    public void testSpringHbm_delete(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-*.xml");
        UserService userService = (UserService) ac.getBean("userService");
        User user = new User();
        user.setId(3);
        userService.delete(user);
    }
}
