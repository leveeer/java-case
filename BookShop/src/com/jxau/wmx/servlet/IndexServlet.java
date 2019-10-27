package com.jxau.wmx.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ListModel;

import com.jxau.wmx.dao.IProductDao;
import com.jxau.wmx.entity.Product;
import com.jxau.wmx.factory.FactoryBean;
import com.jxau.wmx.util.DBUtil;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IProductDao productDao;
	/**
	 * 因为servlet只会构造一次
	 * 所以就可以确保productdao对象只创建一次
	 */
	public IndexServlet() {
		super();
		try {
			productDao=FactoryBean.getInstance("productimpl", 
					IProductDao.class);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageIndex=1;//当前页数
		int pagesize=10;//每页显示的数据
		String currentPage=request.getParameter("pageIndex");
		//页数传递的页数过来
		if(currentPage!=null) {
			try {
				pageIndex=Integer.parseInt(currentPage);
			} catch (Exception e) {
				pageIndex=1;//为了避免异常
			}
			
		}
		//获得总记录数
		int count=productDao.findCount();
		//获得每页的数据
		List<Product>products=productDao.findByPage(pageIndex, pagesize);
		int totalPages=count%pagesize==0?count/pagesize:count/pagesize+1;
		//传递总页数、每页的数据、页数
		HttpSession session=request.getSession();
		session.setAttribute("totalpages", totalPages);
		session.setAttribute("pageIndex", pageIndex);
		session.setAttribute("products", products);
		StringBuffer sb = new StringBuffer(); // 实例化StringBuffer
        for (int i = 1; i <= totalPages; i++) { // 通过循环构建分页导航条
            if (i == pageIndex) { // 判断是否为当前页
                sb.append("『" + i + "』"); // 构建分页导航条
            } else {
                // 构建分页导航条
                sb.append("<a href='index?pageIndex=" + i + "'>" + i + "</a>");
            }
            sb.append("　"); // 构建分页导航条
        }
        
        request.setAttribute("bar", sb.toString()); // 将分页导航条的字符串放置到request中
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
