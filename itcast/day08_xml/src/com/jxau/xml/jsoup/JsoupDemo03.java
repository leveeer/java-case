package com.jxau.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Document/Element对象功能
 */

public class JsoupDemo03 {
    public static void main(String[] args) throws IOException {
        //1.导入jar包
        //2.获取Document对象，根据xml文档获取
        //2.1通过类加载器获取student.xml的Path
        String path = JsoupDemo03.class.getClassLoader().getResource("student.xml").getPath();
        //获取Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
        //获取元素对象

        //获取所有student对象
        Elements elements = document.getElementsByTag("student");
        System.out.println(elements);
        System.out.println("=================");
        //获取属性名为id的元素对象们
        Elements elements1 = document.getElementsByAttribute("id");
        System.out.println(elements1);
        System.out.println("==================");
        //获取number属性值为heima_0001的元素对象
        Elements elements2 = document.getElementsByAttributeValue("number", "heima_0001");
        System.out.println(elements2);
        System.out.println("==================");
        //获取id属性值的元素对象
        Element itcast = document.getElementById("itcast");
        System.out.println(itcast);


    }
}
