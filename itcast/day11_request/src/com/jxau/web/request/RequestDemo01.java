package com.jxau.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * request获取请求行的方法
 */
@WebServlet("/requestDemo01")
public class RequestDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1. 获取请求方式 ：GET
                * String getMethod()
        2. (*)获取虚拟目录：/day11
                * String getContextPath()
        3. 获取Servlet路径: /demo1
                * String getServletPath()
        4. 获取get方式请求参数：name=zhangsan
                * String getQueryString()
        5. (*)获取请求URI：/day11/demo1
                * String getRequestURI():		/day11/demo1
                * StringBuffer getRequestURL()  :http://localhost/day11/demo1

            * URL:统一资源定位符 ： http://localhost/day11/demo1	中华人民共和国
            * URI：统一资源标识符 : /day14/demo1					共和国

        6. 获取协议及版本：HTTP/1.1
            * String getProtocol()

        7. 获取客户机的IP地址：
            * String getRemoteAddr()*/

        //1. 获取请求方式 ：GET
        String method = request.getMethod();
        System.out.println(method);
        System.out.println("=====================");
        //2. (*)获取虚拟目录：/day11
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        System.out.println("=====================");
        //3. 获取Servlet路径: /demo1
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        System.out.println("======================");
        //4. 获取get方式请求参数：name=zhangsan
        String queryString = request.getQueryString();
        System.out.println(queryString);
        System.out.println("=======================");
        //5. (*)获取请求URI：/day11/demo1
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        System.out.println("=======================");
        //6.获取协议及版本：HTTP/1.1
        String protocol = request.getProtocol();
        System.out.println(protocol);
        System.out.println("=======================");
        //7. 获取客户机的IP地址：
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);


    }
}
