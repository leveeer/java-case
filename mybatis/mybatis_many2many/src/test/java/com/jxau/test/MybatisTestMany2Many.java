package com.jxau.test;

import com.jxau.dao.RoleDao;
import com.jxau.dao.UserDao;
import com.jxau.domain.Role;
import com.jxau.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTestMany2Many {

    private InputStream stream;
    private SqlSession session;
    private UserDao userDao;
    private RoleDao roleDao;

    @Before
    public void init() throws IOException {
        //读取配置文件，生成字节输入流
        stream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
        roleDao = session.getMapper(RoleDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.close();
        stream.close();
    }




    @Test
    public void testFindUserById(){

        User user = userDao.findById(52);
        System.out.println(user);

    }

    /**
     * 查询所有角色，并且获取每个角色的用户信息
     */
    @Test
    public void testFindAllRole(){

        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }


    /**
     * 查询所有用户，并查询用户的角色信息
     */
    @Test
    public void testFindAllUser(){

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }


}
