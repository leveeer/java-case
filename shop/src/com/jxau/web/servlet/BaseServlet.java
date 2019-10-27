package com.jxau.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    public  void service(HttpServletRequest request, HttpServletResponse response){
        //获取请求路径
        String uri = request.getRequestURI();
        System.out.println(uri);
        //获取路径下的方法
        int index = uri.lastIndexOf("/");
        String methodName = uri.substring(index + 1);
        System.out.println(methodName);
        try {
            //获取当前访问对象的字节码对象
            Class clazz = this.getClass();

            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
