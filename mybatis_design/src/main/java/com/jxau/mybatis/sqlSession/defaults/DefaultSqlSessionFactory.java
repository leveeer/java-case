package com.jxau.mybatis.sqlSession.defaults;


import com.jxau.mybatis.cfg.Configuration;
import com.jxau.mybatis.sqlSession.SqlSession;
import com.jxau.mybatis.sqlSession.SqlSessionFactory;

/**
 * SqlSessionFactory的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建一个操作数据库的核心对象
     * @return
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
