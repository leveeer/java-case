package com.jxau.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContextDemo03")
public class ServletContextDemo03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
            2. 域对象：共享数据
			1. setAttribute(String name,Object value)
			2. getAttribute(String name)
			3. removeAttribute(String name)

         */

        //通过HttpServlet获取
        ServletContext context = this.getServletContext();
        //设置数据
        context.setAttribute("msg","haha");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
