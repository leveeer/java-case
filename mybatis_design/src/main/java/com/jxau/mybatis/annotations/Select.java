package com.jxau.mybatis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询的注解
 */
//注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
//注解出现的位置----出现在方法上
@Target(ElementType.METHOD)
public @interface Select {

    //配置sql语句
    String value();
}
