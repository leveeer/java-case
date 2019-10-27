package com.jxau.param;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

//获取表单参数(方式一：属性驱动)
//每次请求Action都会创建一个新的Action实例对象，所以允许使用成员变量接收参数，是线程安全的
public class Demo05Action extends ActionSupport {

    public Demo05Action() {
        System.out.println("Action被创建了。。。");
    }

    //准备与表单参数键名称相同的属性
    private String name;

    //自动类型转换只能转换8大基本数据类型以及对应包装类
    private Integer age;

    private Date birthday;

    public String execute() throws Exception{
        System.out.println("name属性为：" + name + "   age属性为：" + age + "   birthday属性为：" + birthday);
        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
