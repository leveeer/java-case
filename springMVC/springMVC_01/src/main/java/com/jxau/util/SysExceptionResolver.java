package com.jxau.util;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        //获取异常对象
        SysException syse = null;
        if (e instanceof SysException){
            syse = (SysException) e;
        }else {
            syse = new SysException("系统维护中");
        }

        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
       mv.addObject("errorMsg",syse.getMessage());
       mv.setViewName("error");

        return mv;
    }
}
