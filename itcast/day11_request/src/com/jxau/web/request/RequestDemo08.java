package com.jxau.web.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestDemo08")
public class RequestDemo08 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Demo8被访问了..........");
        //存储数据到request域中
        request.setAttribute("msg","hello!!!!!");
       //转发到Demo9
        /*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/requestDemo09");
        requestDispatcher.forward(request,response);*/
        request.getRequestDispatcher("/requestDemo09").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       this.doPost(request,response);

    }
}
