package com.jxau.filter;

import com.jxau.utils.DateUtilConvert;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author xie
 */
public class ConvertFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ConvertFilter的init方法执行了");
        ConvertUtils.register(new DateUtilConvert(), Date.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
