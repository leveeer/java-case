package com.jxau.param;

import com.jxau.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//获取表单参数(方式二：对象驱动)
//每次请求Action都会创建一个新的Action实例对象，所以允许使用成员变量接收参数，是线程安全的
public class Demo07Action extends ActionSupport implements ModelDriven<User> {

    private User user = new User();

    public Demo07Action() {
        System.out.println("Action被创建了。。。");
    }

    public String param() throws Exception{
        System.out.println("name属性为：" + user.getName() + "   age属性为：" + user.getAge() + "   birthday属性为：" + user.getBirthday());
        return "success";
    }

    @Override
    public User getModel() {
        return user;
    }
}
