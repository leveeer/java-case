package com.jxau.bank.factory;

import com.jxau.bank.dao.BankDaoInterface;
import com.jxau.bank.dao.impl.BankDaoImpl;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class UserDaoFactory {
    private static BankDaoInterface bankDao;

    private UserDaoFactory() throws Exception{
        //创建属性集
        Properties pro = new Properties();
        //将工厂配置文件加载进内存
        FileInputStream fis = new FileInputStream(new File("classInfo.properties"));
        pro.load(fis);
        fis.close();
        //获取属性集中key为className的值
        String className = pro.getProperty("className");
        //将获取的className加载进内存
        Class clazz = Class.forName(className);
        //获取className的构造方法，忽略访问修饰符
        /*Constructor constructor = clazz.getDeclaredConstructor();
        //忽略访问权限修饰符的安全检查，暴力反射，不安全
        constructor.setAccessible(true);
        //执行构造方法，创建持久层对象
        Object o = constructor.newInstance();*/
        Object o = clazz.newInstance();
        //将返回的对象造型为BankDaoInterface
        bankDao = (BankDaoInterface)o;
        //System.out.println(bankDao);

    }

    private static UserDaoFactory instance;

    //单例创建工厂对象
    public static synchronized UserDaoFactory getInstance() throws Exception {
        if(instance == null){
            instance = new UserDaoFactory();
        }
        return instance;
    }

    //获取反射机制创建的持久层对象
    public static BankDaoInterface createBankDao() {
        return bankDao;
    }
}
