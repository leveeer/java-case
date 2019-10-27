package com.jxau.test;

import com.jxau.dao.impl.UserDaoImpl;
import com.jxau.domain.User;
import com.jxau.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class ConnectTest {
    @Test
    public void testConnect(){
        UserDaoImpl dao = new UserDaoImpl();
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);

        }
    }
    @Test
    public void testService(){
        UserServiceImpl service = new UserServiceImpl();
        List<User> users = service.findAll();
        for (User user : users) {
            System.out.println(user);

        }
    }
}
