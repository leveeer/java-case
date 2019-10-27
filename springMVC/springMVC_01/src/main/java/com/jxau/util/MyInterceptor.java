package com.jxau.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * 应用场景：可对用户权限作验证、是否登录作验证
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     *预处理，controller执行前执行该方法，请求拦截
     *
     * 返回值：true  放行，执行下一个拦截器，如果没有，则直接执行controller中的方法
     *          false  不放行，
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle方法执行了");
        return true;
    }

    /**
     * 后处理方法，结果拦截
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle方法执行了");
    }

    /**
     * jsp页面执行完执行，一般用于释放资源
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion方法执行了");
    }
}
