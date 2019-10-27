package com.jxau.test;

import com.jxau.dao.UserDao;
import com.jxau.domain.QueryVo;
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
import java.util.Date;
import java.util.List;

public class MybatisTestCRUD {

    private InputStream stream;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws IOException {
        //读取配置文件，生成字节输入流
        stream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.close();
        stream.close();
    }

    @Test
    public void testFindAll() {

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }


    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("张三");
        user.setAddress("深圳");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存前：" + user);  //保存前：User{id=null, username='张三', birthday=Sun Sep 15 13:45:56 CST 2019, sex='男', address='深圳'}
        userDao.save(user);
        System.out.println("保存后：" + user);  //保存后：User{id=53, username='张三', birthday=Sun Sep 15 13:45:56 CST 2019, sex='男', address='深圳'}
        session.commit();

    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(52);
        user.setUsername("王老五");
        user.setAddress("深圳");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);
        session.commit();
    }

    @Test
    public void testDeleteUser(){

        userDao.deleteUser(48);
        session.commit();
    }

    @Test
    public void testFindUserById(){

        User user = userDao.findById(52);
        System.out.println(user);
        //session.commit();
    }

    @Test
    public void testFindByName(){

        List<User> users = userDao.findByName("%王%");
        /*List<User> users = userDao.findByName("王");*/
        for (User user : users) {
            System.out.println(user);
        }
        //session.commit();
    }

    @Test
    public void testFindTotal(){

        int total = userDao.findTotal();
        System.out.println(total);
        //session.commit();
    }

    @Test
    public void testFindByVo() {

        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);

        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
