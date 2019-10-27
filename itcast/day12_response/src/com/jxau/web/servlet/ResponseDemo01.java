package com.jxau.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 */
@WebServlet("/responseDemo01")
public class ResponseDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("demo01......");
        //访问/responseDemo01,会自动跳转到/responseDemo02资源
       /* //设置状态码为302
        response.setStatus(302);
        //设置响应头location
        response.setHeader("location","/day12/responseDemo02");*/
       //动态获取虚拟目录
        String contextPath = request.getContextPath();

        response.sendRedirect(contextPath+"/responseDemo02");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
