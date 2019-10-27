package com.jxau.web;

import com.jxau.pojo.User;
import com.jxau.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("user")
public class DemoController {


    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User demo(@PathVariable("id") Long id){
        log.debug("方法执行");
        return userService.findById(id);
    }
}
