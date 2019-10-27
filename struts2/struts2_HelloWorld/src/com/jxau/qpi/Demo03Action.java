package com.jxau.qpi;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.apache.struts2.ServletActionContext.getRequest;

//Action获取ServletAPI
/*
*
*
* */
public class Demo03Action extends ActionSupport {

        public String execute() throws Exception{

            //request
            HttpServletRequest request = ServletActionContext.getRequest();
            //session
            HttpSession session = request.getSession();
            //response
            HttpServletResponse response = ServletActionContext.getResponse();
            //ServletContext
            ServletContext servletContext = ServletActionContext.getServletContext();


            return "success";
        }

}
