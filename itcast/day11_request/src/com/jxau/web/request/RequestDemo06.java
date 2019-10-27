package com.jxau.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/requestDemo06")
public class RequestDemo06 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //post 获取请求参数
        String username = request.getParameter("username");
        /*System.out.println("post");
        System.out.println(username);*/
        //根据参数名称获取参数值的数组
       /* String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        //获取所有请求的参数名
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String nextElement = parameterNames.nextElement();
            System.out.println(nextElement);
            String parameter = request.getParameter(nextElement);
            System.out.println(parameter);
            System.out.println("===============");
        }*/

        //获取所有参数的map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        //遍历Map集合
        Set<String> keySet = parameterMap.keySet();//Map集合通过keySet()获取键
        //根据键获取值
        for (String name : keySet) {
            String[] values = parameterMap.get(name);
            System.out.println(name);
            for (String value : values) {
                System.out.println(value);
            }
            System.out.println("=====================");


        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get 获取请求参数
       this.doPost(request,response);

    }
}
