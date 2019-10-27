package com.jxau.controller;

import com.jxau.util.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("/testException")
    public String testException() throws SysException{
        //模拟异常
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("程序出错...");
        }
        return "success";

    }
}
