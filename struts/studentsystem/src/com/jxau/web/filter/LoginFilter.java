package com.jxau.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制转换
        HttpServletRequest request = (HttpServletRequest) req;

        //获取资源请求路径
        String uri = request.getRequestURI();

        //判断是否包含登录相关资源路径,要注意排除掉 css/js/图片/验证码等资源
        if(uri.contains("/login.jsp") || uri.contains("/adminServlet/login")|| uri.contains("/studentServlet/login") || uri.contains("/register.jsp")|| uri.contains("/images/")|| uri.contains("/css/")|| uri.contains("/js/")|| uri.contains("/fonts/")|| uri.contains("/checkCodeServlet")){
            //包含,证明用户就是想登录
            //放行
            chain.doFilter(req,resp);
        }else {
            //不包含,不是想登录
            //验证用户是否登录
            //从session中获取user对象
            Object student = request.getSession().getAttribute("student");
            Object admin = request.getSession().getAttribute("admin");
            if(student != null || admin != null){
                //证明用户登录了,放行
                chain.doFilter(req,resp);
            }else {
                //没有登录,跳转登录页面
                request.setAttribute("login_error","您尚未登录,请登录!");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
