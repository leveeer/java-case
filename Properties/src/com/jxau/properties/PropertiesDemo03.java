package com.jxau.properties;

import java.util.Properties;
import java.util.Set;

public class PropertiesDemo03 {
    public static void main(String[] args) {
        //创建Properties对象
        Properties properties = new Properties();
        //添加元素
        properties.setProperty("filename","a.txt");
        properties.setProperty("length","1024");
        properties.setProperty("location","E:\\a.txt");
        //打印属性集对象
        System.out.println(properties);
        //遍历属性集
        Set<String> strings = properties.stringPropertyNames();
        for(String key : strings){
            System.out.println(key + "=" + properties.getProperty(key));

        }
    }
}
