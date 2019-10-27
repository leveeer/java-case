package com.jxau.p2p.base;

import com.jxau.p2p.base.service.ILogininfoService;
import com.jxau.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户注册
 */
@Controller
public class RegisterController {

    @Autowired
    private ILogininfoService logininfoService;

    @RequestMapping("register")
    @ResponseBody
    public JSONResult register(String username, String password){
        JSONResult json = new JSONResult();
        try{
            logininfoService.register(username,password);
        }catch (RuntimeException e){
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }

    @RequestMapping("testAjax")
    @ResponseBody
    public String testAjax(String username){
        System.out.println(username);
        return "hello ! " + username;
    }
}
