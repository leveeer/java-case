package com.jxau.mybatis.sqlSession.defaults;

import com.jxau.mybatis.cfg.Configuration;
import com.jxau.mybatis.sqlSession.SqlSession;
import com.jxau.mybatis.sqlSession.proxy.MapperProxy;
import com.jxau.mybatis.utils.DataSourceUtil;


import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * SqlSession的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass  dao接口字节码
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {

        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                                new Class[]{daoInterfaceClass},
                                new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 用于释放资源
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
