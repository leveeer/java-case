package com.jxau.mybatis.sqlSession.proxy;

import com.jxau.mybatis.cfg.Mapper;
import com.jxau.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {

    //Map的key是全限定类名+要执行的方法名
    private Map<String, Mapper> mappers;

    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * 用于对方法增强
     * 增强selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        //获取方法名
        String methodName = method.getName();

        //获取方法所在类的名称
        String className = method.getDeclaringClass().getName();

        //组合key
        String key = className + "." + methodName;

        //获取mappers中的mapper对象
        Mapper mapper = mappers.get(key);
        //判断是否有mapper
        if (mapper == null){
            //传入的参数有问题
            throw new IllegalArgumentException("传入的参数有误");
        }

        //调用工具类执行接口中的方法
        if ("findAll".equals(methodName)) {
            return new Executor().selectList(mapper, conn);
        }else if ("findOne".equals(methodName)){
            return new Executor().selectOne(mapper, conn);
        }else {
            throw new RuntimeException("没有此方法");
        }
    }
}
