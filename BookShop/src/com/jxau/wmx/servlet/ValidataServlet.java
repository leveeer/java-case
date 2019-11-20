package com.jxau.wmx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.wmx.dao.IUserDao;
import com.jxau.wmx.dao.impl.UserDaoImpl;
import com.jxau.wmx.factory.FactoryBean;

@WebServlet("/validata")
public class ValidataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserDao userDao;
   
    public ValidataServlet() {
        super();
       try {
		userDao=FactoryBean.getInstance("userdaoimpl", IUserDao.class);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//传递的参数包含中文，设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");//设置响应编码
		String name=request.getParameter("name");
		if(userDao.findByName(name)) {
			response.getWriter().print("true");
		}else {
			response.getWriter().print("false");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
