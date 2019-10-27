package com.jxau.aspect;

import org.aspectj.lang.JoinPoint;

public aspect AspectClassParam {
    public void check(JoinPoint joinpoint){
        Object[] args = joinpoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }

    }
}
