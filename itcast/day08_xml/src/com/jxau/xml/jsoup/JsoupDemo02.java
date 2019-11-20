package com.jxau.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Jsoup对象
 */

public class JsoupDemo02 {
    public static void main(String[] args) throws IOException {
        //1.导入jar包
        //2.获取Document对象，根据xml文档获取
        //2.1通过类加载器获取student.xml的Path
        String path = JsoupDemo02.class.getClassLoader().getResource("student.xml").getPath();
        //2.2解析xml文档加载文档进内存，获取dom树---->Document
        /*Document document = Jsoup.parse(new File(path), "utf-8");
        System.out.println(document);*/
        /*//2.parse(String html),解析xml或html字符串
        String str = "<!--?xml version=\"1.0\" encoding=\"UTF-8\" ?--><!DOCTYPE students SYSTEM \"student.dtd\">\n" +
                "<!--<!DOCTYPE students [\n" +
                "\t\t<!ELEMENT students (student*) >\n" +
                "\t\t<!ELEMENT student (name,age,sex)>\n" +
                "\t\t<!ELEMENT name (#PCDATA)>\n" +
                "\t\t<!ELEMENT age (#PCDATA)>\n" +
                "\t\t<!ELEMENT sex (#PCDATA)>\n" +
                "\t\t<!ATTLIST student number ID #REQUIRED>\n" +
                "\t\t]>-->\n" +
                "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <students> \n" +
                "   <student number=\"heima_0001\"> \n" +
                "    <name>\n" +
                "     tom\n" +
                "    </name> \n" +
                "    <age>\n" +
                "     18\n" +
                "    </age> \n" +
                "    <sex>\n" +
                "     male\n" +
                "    </sex> \n" +
                "   </student> \n" +
                "   <student number=\"heima_0002\"> \n" +
                "    <name>\n" +
                "     jack\n" +
                "    </name> \n" +
                "    <age>\n" +
                "     18\n" +
                "    </age> \n" +
                "    <sex>\n" +
                "     female\n" +
                "    </sex> \n" +
                "   </student> \n" +
                "  </students> \n" +
                " </body>\n" +
                "</html>";
        Document parse = Jsoup.parse(str);
        System.out.println(parse);*/
        //parse(URL url,int timeoutMillis),通过网络路径获取指定的html或xml文档对象
        URL url = new URL("https://baike.baidu.com/item/jsoup/9012509?fr=aladdin");
        Document document = Jsoup.parse(url, 10000);
        System.out.println(document);


    }
}
