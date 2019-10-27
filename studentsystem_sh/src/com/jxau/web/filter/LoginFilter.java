package com.jxau.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*@WebFilter("/*")*/
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //??????
        HttpServletRequest request = (HttpServletRequest) req;

        //??????????¡¤??
        String uri = request.getRequestURI();

        //?§Ø????????????????¡¤??,????????? css/js/??/?????????
        if(uri.contains("/login.jsp") || uri.contains("/adminServlet/login")|| uri.contains("/studentServlet/login") || uri.contains("/register.jsp")|| uri.contains("/images/")|| uri.contains("/css/")|| uri.contains("/js/")|| uri.contains("/fonts/")|| uri.contains("/checkCodeServlet")){
            //????,??????????????
            //????
            chain.doFilter(req,resp);
        }else {
            //??????,????????
            //???????????
            //??session?§Ý??user????
            Object student = request.getSession().getAttribute("student");
            Object admin = request.getSession().getAttribute("admin");
            if(student != null || admin != null){
                //???????????,????
                chain.doFilter(req,resp);
            }else {
                //??§Ö??,?????????
                request.setAttribute("login_error","????¦Ä???,????!");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
