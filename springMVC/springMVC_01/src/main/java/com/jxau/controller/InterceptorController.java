package com.jxau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        System.out.println("###################");
        return "success";
    }
}
