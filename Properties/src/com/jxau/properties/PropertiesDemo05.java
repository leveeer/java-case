package com.jxau.properties;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

/**
 * 从控制台输入数据存储到properties属性集,以键值对形式保存
 */
public class PropertiesDemo05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String next = null;
        //创建属性集
        Properties properties = new Properties();
        /*System.out.println(properties.size());
        //当输入的数据大于属性集size时，终止循环
        while (properties.size() < 3) {
            //将输入的数据存入next中
            next = sc.nextLine();
            //将next中的数据以","分割，存放在数组中
            String[] arr = next.split(",");
            //将数组中的数据存入属性集
            properties.setProperty(arr[0], arr[1]);
        }

        try {
            //创建文件输出流对象，创建info.txt文件
            FileOutputStream fos = new FileOutputStream("info.txt");
            //将属性集中的数据写入info.txt
            properties.store(fos, "info");
            //关闭输入流
            if (fos == null){
            fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("info.txt");
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String key = properties.getProperty("zhangsan");
        System.out.println(key);
        System.out.println(properties);
        */
        /*InputStream inputStream = PropertiesDemo05.class.getClassLoader().getResourceAsStream("info.txt");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String key = properties.getProperty("zhangsan");
        System.out.println(key);*/

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("info.txt");
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //增强for遍历
        //获取所有的键集合
        Set<String> keySet = properties.stringPropertyNames();
        //遍历每一个键
        for (String info : keySet) {
            //通过键获取值
            System.out.println(info + "=" + properties.getProperty(info));
        }
        //System.out.println(properties);
    }
}
