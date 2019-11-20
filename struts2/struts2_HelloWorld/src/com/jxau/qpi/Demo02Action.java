package com.jxau.qpi;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

//Action获取ServletAPI
/*
* ActionContext（Map）数据中心
*       生命周期：每次请求都会创建一个与请求对应的ActionContext对象，请求处理完ActionContext销毁
*       如何获取：struts2设计的是将ActionContext对象创建好之后，将ActionContext与当前线程绑定，我们要获得ActionContext只需要从
*                   ThreadLocal中获取即可
*
* */
public class Demo02Action extends ActionSupport {

        public String execute() throws Exception{

            //获取原生的request域（不推荐使用原生的request域）
            Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
            //struts2推荐直接把ActionContext作为request域使用
            //设置键值对
            ActionContext.getContext().put("user","requestuser");


            //session域对象的获取
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("user","sessionuser");

            //application域
            Map<String, Object> application = ActionContext.getContext().getApplication();
            application.put("user","applicationuser");

            return "success";
        }

}
