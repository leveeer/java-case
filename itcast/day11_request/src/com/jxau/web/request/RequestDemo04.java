package com.jxau.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * request获取请求头
 */
@WebServlet("/requestDemo04")
public class RequestDemo04 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求头:referer
        String header = request.getHeader("referer");
        System.out.println(header);//http://localhost:8080/day11/login.html
        //防盗链
        if(header!=null){
            if(header.contains("/day11")){
                //正常访问
                System.out.println("播放电影....");
            }else{
                //盗链
                System.out.println("盗链可耻!");
            }
        }
    }

}
