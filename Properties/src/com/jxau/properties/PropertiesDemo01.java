package com.jxau.properties;

import java.util.Properties;

public class PropertiesDemo01 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("张三",23);
        properties.put("李四",24);
        properties.put("王五",25);
        properties.put("赵六",26);
        System.out.println(properties);
    }
}
