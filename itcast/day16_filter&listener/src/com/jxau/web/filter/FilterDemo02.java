package com.jxau.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo02 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("demo02执行了");
        //放行
        chain.doFilter(req, resp);
        System.out.println("demo02回来了");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
