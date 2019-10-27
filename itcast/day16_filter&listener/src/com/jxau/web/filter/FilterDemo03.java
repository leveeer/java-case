package com.jxau.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class FilterDemo03 implements Filter {
    public void destroy() {
        System.out.println("destory....");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("doFilter...");

        //放行
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

        System.out.println("init....");
    }

}
