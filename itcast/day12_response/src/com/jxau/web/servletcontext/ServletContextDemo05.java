package com.jxau.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/servletContextDemo05")
public class ServletContextDemo05 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        //通过HttpServlet获取
        ServletContext context = this.getServletContext();
        //获取文件b.txt的服务器路径
        String b = context.getRealPath("/b.txt");
        System.out.println(b);//web目录下资源访问
        //获取文件c.txt的服务器路径
        String c = context.getRealPath("/WEB-INF/c.txt");
        System.out.println(c);//WEB-INF目录下的资源访问

        //获取文件a.txt的服务器路径
        String a = context.getRealPath("/WEB-INF/classes/a.txt");
        System.out.println(a);//src目录下的资源访问
        //File file = new File(realPath);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
