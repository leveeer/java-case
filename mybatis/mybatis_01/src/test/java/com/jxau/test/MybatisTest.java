package com.jxau.test;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

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
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
        is.close();
    }

    @Test
    public void testMap() throws UnsupportedEncodingException {
        User user = new User();
        user.setUsername("zhangsan");
        User user1 = new User();
        user1.setUsername("zhangsan");
        System.out.println(user == user1);
        System.out.println(user.equals(user1));
        System.out.println("user的hashcode值为" + user.hashCode());
        System.out.println("user1的hashcode值为" + user1.hashCode());
        HashMap<User, String> map = new HashMap<User, String>();
        map.put(user, "qq");
        //map.put(user1,"qq");
        //System.out.println(map);
        String s = map.get(user1);
        System.out.println(s);
        String s1 = "张三";
        String gbk = new String(s1.getBytes(), "utf-8");
        System.out.println(gbk);
    }
}
