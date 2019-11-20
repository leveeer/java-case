package com.jxau.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    //定义一个properties对象
    private static Properties pro;
    //定义一个Map用于存放要创建的对象，称之为容器
    private static Map<String, Object> beans;

    //使用静态代码块为properties对象赋值
    static {
        try {
            //实例化对象
            pro = new Properties();
            //获取properties文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            pro.load(in);
            //实例化容器
            beans = new HashMap<String,Object>();
            //取出配置文中所有的key
            Enumeration keys = pro.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = pro.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                //把key和value存入容器中
                beans.put(key, value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 根据bean的名称获取bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        /*Object bean = null;
        try {
            String beanPath = pro.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;*/
        return beans.get(beanName);
    }
}
