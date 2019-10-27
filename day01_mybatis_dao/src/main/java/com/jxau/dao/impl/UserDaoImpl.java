package com.jxau.dao.impl;

import com.jxau.dao.IUserDao;
import com.jxau.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;

    }
    public List<User> findAll() {
        //使用工厂创建session对象
        SqlSession session = factory.openSession();
        //使用session执行查询所有方法
        List<User> users = session.selectList("com.jxau.dao.IUserDao.findAll");
        session.close();
        //返回查询结果
        return users;
    }
}
