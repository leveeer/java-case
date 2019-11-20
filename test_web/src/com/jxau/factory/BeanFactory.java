package com.jxau.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    private static Object obj;
    private Properties pro;

    private BeanFactory(){
        //创建属性集
        pro = new Properties();
        //将工厂配置文件加载进内存
        InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("classInfo.properties");
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取属性集中key为className的值
        String className = pro.getProperty("className");
        //将获取的className加载进内存
        try {
            Class  clazz = Class.forName(className);
            obj = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static BeanFactory instance;

    //单例创建工厂对象
    public static synchronized BeanFactory getInstance(){
        if (instance == null) {
            instance = new BeanFactory();
        }
        return instance;
    }

    //创建对象
    public Object getBean(String pathName) {
        return obj;
    }
}