package com.jxau.properties;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo02 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream("a.txt");
        properties.load(inputStream);
        Set<String> names = properties.stringPropertyNames();
        for (String key : names){
            System.out.println(key + "=" + properties.getProperty(key));
        }

        FileOutputStream stream = new FileOutputStream("b.txt");
        properties.setProperty("qq","1519695805");
        properties.setProperty("tel","15797954706");
        properties.store(stream,"new file");
        stream.close();

    }
}
