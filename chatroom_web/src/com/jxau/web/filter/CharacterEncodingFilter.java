package com.jxau.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编码拦截器
 */

public class CharacterEncodingFilter implements Filter {
    private String encoding;
    @Override
    public void init(FilterConfig filterConfig) {
        this.encoding = filterConfig.getInitParameter("encoding");
        System.out.println(encoding);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        //向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //设置字符集
        request.setCharacterEncoding(this.encoding);
        //放行
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
