package com.jxau.mybatis.sqlSession;

import com.jxau.mybatis.cfg.Configuration;
import com.jxau.mybatis.sqlSession.defaults.DefaultSqlSessionFactory;
import com.jxau.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来创建一个SqlSessionFactory工厂
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
