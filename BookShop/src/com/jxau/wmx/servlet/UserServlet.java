package com.jxau.wmx.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.wmx.dao.IUserDao;
import com.jxau.wmx.entity.User;
import com.jxau.wmx.factory.FactoryBean;
import com.jxau.wmx.util.DBUtil;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao;   
   
    public UserServlet() {
        super();
        try {
			userDao=FactoryBean.getInstance("userdaoimpl", IUserDao.class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		String url=request.getRequestURL().toString();
		if(url.contains("logout.action")) {
			logOut(request,response);
		}else {
		switch (cmd) {
		case "register":
			register(request, response);
			break;
		case "login":
			login(request, response);
			break;
		default:
			break;
		}
		}
	}

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取注册页面表单提交的信息
    	String username=request.getParameter("userName");
    	String pwd=request.getParameter("passWord");
    	String email=request.getParameter("email");
    	User user=new User();
    	user.setUsername(username);
    	user.setPwd(pwd);
    	user.setEmail(email);
    	if(userDao.register(user)>0) {
    		request.getRequestDispatcher("register_success.jsp").forward(request, response);
    	}else {
    		request.getRequestDispatcher("register.jsp").forward(request, response);;
    	}
    	
    	
	}

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username=request.getParameter("userName");
		String pwd=request.getParameter("passWord");
		 /*
        拿到页面传过来的手动输入的验证码, 该验证码要和生成图片上的
        文本验证码比较, 如果相同, 验证码输入成功!
     */
    String imageText = request.getParameter("validationCode");
    // 图片的验证码
    String text = (String) request.getSession().getAttribute("validation_code");
    if (!text.equalsIgnoreCase(imageText)) {
        request.setAttribute("imageMess", "验证码输入错误!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }else {
		User user=userDao.login(username, pwd);
		if(user!=null) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("index?pageIndex=1");
		}else{
			response.sendRedirect("login.jsp");
		}
   	}
    }
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
protected void logOut(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		
		if(request.getSession() == null) {
			response.sendRedirect("login.jsp");
			return;
			}
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
}}
