package com.jxau.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * request获取请求头
 */
@WebServlet("/requestDemo03")
public class RequestDemo03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求头user-agent
        String header = request.getHeader("user-agent");
        //判断agent的浏览器版本
        if(header.contains("Chrome")) {
            System.out.println("谷歌来了...");
        }else if(header.contains("Firefox")) {
            System.out.println("火狐来了...");
        }

    }

}
