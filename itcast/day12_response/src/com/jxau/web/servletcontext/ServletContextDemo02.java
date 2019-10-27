package com.jxau.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContextDemo02")
public class ServletContextDemo02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*

            1. 获取MIME类型：
			* MIME类型:在互联网通信过程中定义的一种文件数据类型
				* 格式： 大类型/小类型   text/html		image/jpeg
         */


        //ServletContext的获取方式
        /*
                1. 通过request对象获取
			         request.getServletContext();
		        2. 通过HttpServlet获取
			         this.getServletContext();
         */
        //通过HttpServlet获取
        ServletContext context = this.getServletContext();
        //定义文件名称
        String filename = "a.jpg";
        //获取MIME类型
        String mimeType = context.getMimeType(filename);
        System.out.println(mimeType);//image/jpeg
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
