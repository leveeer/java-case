package com.jxau.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectClass {

    @Before("allAddMethod()")
    public void check(){
        System.out.println("用户身份验证");
    }

    @After("allAddMethod()")
    public void writeLog(){
        System.out.println("记录日志");
    }

    @Pointcut("execution(* add*(..)) || execution(* delete*(..))")
    public void allAddMethod(){}
}
