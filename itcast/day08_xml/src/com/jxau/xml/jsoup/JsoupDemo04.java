package com.jxau.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Element对象
 */

public class JsoupDemo04 {
    public static void main(String[] args) throws IOException {
        //1.导入jar包
        //2.获取Document对象，根据xml文档获取
        //2.1通过类加载器获取student.xml的Path
        String path = JsoupDemo04.class.getClassLoader().getResource("student.xml").getPath();
        //2.2解析xml文档加载文档进内存，获取dom树---->Document
        Document document = Jsoup.parse(new File(path), "utf-8");
        //通过Document对象获取name标签，获取所有的name标签，可以获取到两个
        Elements elements = document.getElementsByTag("name");
        //System.out.println(elements);
        System.out.println(elements.size());
        System.out.println("====================");
        //通过Element对象获取子标签对象
        Element element_student = document.getElementsByTag("student").get(0);
        Elements ele_name = element_student.getElementsByTag("name");
        System.out.println(ele_name.size());
        System.out.println("====================");
        //获取student对象的属性值
        String number = element_student.attr("number");
        System.out.println(number);
        System.out.println("====================");
        //获取name标签的文本内容
        String text = ele_name.text();
        System.out.println(text);//获取所有子标签的纯文本内容
        System.out.println("====================");
        String html = ele_name.html();
        System.out.println(html);//获取标签体的所有内容（包括子标签的标签和文本内容）


    }
}
