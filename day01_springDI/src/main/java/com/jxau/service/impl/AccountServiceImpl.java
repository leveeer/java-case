package com.jxau.service.impl;


import com.jxau.service.AccountService;

import java.util.Date;

public class AccountServiceImpl implements AccountService {

    //如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl (String name, Integer age, Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
    public void saveAccount() {
        System.out.println("service中的方法执行了。。。。" + name + "," + age + "," + birthday);
    }
}
