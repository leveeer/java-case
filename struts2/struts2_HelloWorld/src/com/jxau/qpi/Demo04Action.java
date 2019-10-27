package com.jxau.qpi;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

//Action获取ServletAPI
/*
*
*
* */
public class Demo04Action implements ServletRequestAware, ServletResponseAware, ServletContextAware, SessionAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;

    public String execute() throws Exception{

            //request

            //session

            //response

            //ServletContext



            return "success";
        }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void setSession(Map<String, Object> map) {

    }
}
