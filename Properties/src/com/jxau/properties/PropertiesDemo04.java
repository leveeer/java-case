package com.jxau.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesDemo04 {
    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
           
            InputStream in = new BufferedInputStream(new FileInputStream("a.properties"));
            prop.load(in);    
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                System.out.println(key + ":" + prop.getProperty(key));
            }
            in.close();

            
            FileOutputStream oFile = new FileOutputStream("b.properties", true);
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
            oFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
