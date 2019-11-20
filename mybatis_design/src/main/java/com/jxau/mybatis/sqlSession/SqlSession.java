package com.jxau.mybatis.sqlSession;

/**
 * 自定义mybatis中和数据库交互的核心接口
 * 用于创建dao接口的代理对象
 */
public interface SqlSession {

    //泛型必须先声明后使用

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass  dao接口字节码
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
