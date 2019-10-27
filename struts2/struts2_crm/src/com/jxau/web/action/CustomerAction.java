package com.jxau.web.action;

import com.jxau.domain.Customer;
import com.jxau.service.CustomerService;
import com.jxau.service.impl.CustomerServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CustomerAction extends ActionSupport {

    private CustomerService cs = new CustomerServiceImpl();
    public String list() throws Exception{
        //接收参数
        String cust_name = ServletActionContext.getRequest().getParameter("cust_name");
        //创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        //判断参数封装条件
        if(StringUtils.isNotEmpty(cust_name)){
            dc.add(Restrictions.like("cust_name","%" + cust_name + "%"));
        }
        //调用service将离线对象传递
        List<Customer> list = cs.getAll(dc);
        //将返回的list放入request域，转发到list.jsp显示
        ServletActionContext.getRequest().setAttribute("list",list);
        return "list";
    }
}
