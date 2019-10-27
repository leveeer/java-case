package com.jxau.exceptions;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {

        SysException se = null;
        if (e instanceof SysException){
            se = (SysException) e;
        }else {
            throw new SysException("系统维护中");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",se.getMessage());
        mv.setViewName("failure");
        return mv;
    }
}
