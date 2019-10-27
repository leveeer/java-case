package com.jxau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//控制器类

/**
 * 前端控制器-----指挥中心（分发请求）
 */
@Controller
@RequestMapping(path = "/user")
public class HelloController {

    @RequestMapping(path = "/hello",method = {RequestMethod.POST,RequestMethod.GET},params = {"username=zs"})
    public String sayHello(){
        System.out.println("hello springMVC");
        return "success";
    }
}
