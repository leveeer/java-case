package com.jxau.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 请求验证拦截器
 */
//@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取资源请求路径
        String uri = request.getRequestURI();
        System.out.println(uri);

        //判断是否包含登录相关资源路径,要注意排除掉 css/js/图片/验证码等资源
        if(uri.contains("/login.jsp") || uri.contains("/adminServlet/adminLogin") || uri.contains("/userServlet/login")
                || uri.contains("/register.jsp") || uri.contains("/userServlet/register") || uri.contains("/userServlet/findUserByUsername")
                || uri.contains("/img/")|| uri.contains("/css/")|| uri.contains("/js/")|| uri.contains("/fonts/")|| uri.contains("/checkCodeServlet")){
            //包含,证明用户就是想登录
            //放行
            chain.doFilter(request,response);
        }else {
            //不包含,不是想登录
            //验证是否登录
            //从session中获取user对象
            Object user = request.getSession().getAttribute("user");
            Object admin = request.getSession().getAttribute("admin");
            if(user != null || admin != null){
                //证明登录了,下发请求
                chain.doFilter(request,response);
            }else {
                //没有登录,跳转登录页面
                request.setAttribute("login_error","您尚未登录,请先登录!");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
