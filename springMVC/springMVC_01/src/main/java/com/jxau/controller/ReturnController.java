package com.jxau.controller;

import com.jxau.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 测试服务端跳转
 */
@Controller
@RequestMapping(path = "/return")
public class ReturnController {

    @RequestMapping("returnMethod")
    public String test(User user, Model model){
        System.out.println("方法跳转成功");
        User user1 = new User();
        user1.setUname("张三");
        user1.setAge(20);
        user1.setBirthday(new Date());
        model.addAttribute("user",user1);

        return "success";
    }

    @RequestMapping("testReturn")
    public void testReturn(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("testReturn方法执行了");
        request.getRequestDispatcher("/return/returnMethod").forward(request,response);
    }

    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mv = new ModelAndView();
        User user1 = new User();
        user1.setUname("张三");
        user1.setAge(20);
        user1.setBirthday(new Date());
        mv.addObject("user",user1);
        mv.setViewName("success");
        return mv;
    }
}
