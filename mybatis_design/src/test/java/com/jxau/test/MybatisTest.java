package com.jxau.test;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import com.jxau.mybatis.io.Resources;
import com.jxau.mybatis.sqlSession.SqlSession;
import com.jxau.mybatis.sqlSession.SqlSessionFactory;
import com.jxau.mybatis.sqlSession.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建sqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //使用工厂生产sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //使用sqlSession创建dao接口的代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //使用代理对象执行方法
        /*List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }*/

        System.out.println("======================");
        User one = userDao.findOne();
        System.out.println(one);
        //释放资源
        sqlSession.close();
        is.close();
    }
}
