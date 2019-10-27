package com.jxau.test;

import com.jxau.dao.AccountDao;
import com.jxau.dao.UserDao;
import com.jxau.domain.Account;
import com.jxau.domain.AccountUser;
import com.jxau.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class MybatisTestOne2Many {

    private InputStream stream;
    private SqlSession session;
    private UserDao userDao;
    private AccountDao accountDao;

    @Before
    public void init() throws IOException {
        //读取配置文件，生成字节输入流
        stream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
        accountDao = session.getMapper(AccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.close();
        stream.close();
    }

    /*@Test
    //查询所有账户信息，包括关联属性用户的信息
    public void testFindAll() {

        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }

    }*/

    @Test
    public void testFindAllAccount() {

        List<AccountUser> allAccount = accountDao.findAllAccount();
        for (AccountUser accountUser : allAccount) {
            System.out.println(accountUser);

        }

    }


    @Test
    public void testFindUserById(){

        User user = userDao.findById(52);
        System.out.println(user);
        //session.commit();
    }

    /**
     * 查询所有用户信息，包括用户的账户信息
     */
    @Test
    public void testFindAll(){

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        System.out.println((34^17) % 6);
        System.out.println(5|7);
    }


}
