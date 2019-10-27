package com.jxau.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * Servlet的抽取，完成方法的分发
 * @author xie
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {

        //完成方法的分发
        //获取请求路径
        String uri = request.getRequestURI();
        //System.out.println(uri);
        //获取方法名称，请求中"/"最后出现的索引 + 1
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println(methodName);
        //获取方法名称对象
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
