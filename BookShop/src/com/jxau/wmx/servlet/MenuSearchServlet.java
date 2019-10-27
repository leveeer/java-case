package com.jxau.wmx.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jxau.wmx.dao.IProductDao;
import com.jxau.wmx.dao.IUserDao;
import com.jxau.wmx.dao.impl.ProductDaoImpl;
import com.jxau.wmx.entity.Product;
import com.jxau.wmx.factory.FactoryBean;
import com.jxau.wmx.util.C3P0Util;

/**
 * Servlet implementation class MenuSearchServlet
 */
/*
 * 搜索栏功能类
 */
@WebServlet(urlPatterns= {"/simpleSearch.do","/supperSearch.do"})
public class MenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductDao productDao;
		
    public MenuSearchServlet() {
        super();
        try {
			productDao = FactoryBean.getInstance("productimpl", IProductDao.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//解决中文乱码问题
		response.setContentType("text/html;charset=utf-8");//设置响应编码	
		String url=request.getRequestURL().toString();
		//简单搜寻
		if(url.contains("simpleSearch.do")) {
			getProductBySearchName(request, response);
		}
		//超级搜寻
		else if(url.contains("supperSearch.do")) {
			supperSearch(request, response);
		}
		
				
	}
	
	protected void getProductBySearchName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageIndex=1;//当前页数
		int pagesize=100;//每页显示的数据
		String currentPage=request.getParameter("pageIndex");
		String bookname=request.getParameter("keywords");
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
		List<Product>products=productDao.getProductBySearchName(pageIndex,pagesize,bookname);
		int totalPages=count%pagesize==0?(count/pagesize):(count/pagesize+1);
		//传递总页数、每页的数据、页数
		HttpSession session=request.getSession();
		session.setAttribute("totalpages", totalPages);
		session.setAttribute("pageIndex", pageIndex);
		session.setAttribute("products", products);
		if(products!=null&&!products.isEmpty()) {
			StringBuffer sb = new StringBuffer(); // 实例化StringBuffer
	        for (int i = 1; i <= totalPages; i++) { // 通过循环构建分页导航条
	            if (i == pageIndex) { // 判断是否为当前页
	                sb.append("[" + i + "]"); // 构建分页导航条
	            } else {
	                // 构建分页导航条
	                sb.append("<a href='index?pageIndex=" + i + "'>" + i + "</a>");
	            }
	            sb.append(" "); // 构建分页导航条
	        }
	        
	        request.setAttribute("bar", sb.toString()); // 将分页导航条的字符串放置到request中
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("searchFail.jsp").forward(request, response);
		}
	}
	
	
	private void supperSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Product> products0=new ArrayList<Product>();
		//通过连接池连接数据库
		Connection conn=C3P0Util.getConn();
		//取到搜索框中传来的参数
		String bookname=request.getParameter("bookName");
		//要对double型的价格进行转换
		double price1=Double.parseDouble(request.getParameter("price1"));
		double price2=Double.parseDouble(request.getParameter("price2"));
		String categoryId=request.getParameter("category");//取得类别下拉列表的值
		
		/*
		//输出表单中传来的参数
		System.out.println("书名:"+bookname);
		System.out.println("price1:"+price1);
		System.out.println("price2:"+price2);
		System.out.println("分类:"+categoryId);
		*/
		
		/*		
		//测试单个查询功能
		products0=productDao.findByBookCategory(categoryId);
		if(products0.size()>0) {
			System.out.println("商品已找到!");
			this.print(products0);
			request.setAttribute("products0",products0);
			request.getRequestDispatcher("productCategory.jsp").forward(request, response);
		}else {
			System.out.println("很遗憾,没有找到");
			request.getRequestDispatcher("productCategory.jsp").forward(request, response);
		}
		*/
					
		//调用Dao层方法找到对应的商品
		products0=productDao.findByBookName(bookname);//根据书名找到商品并封装
		if(products0.size()==0) { //通过size()方法判断是否找到商品
			//根据价格找到商品并封装
			 products0=productDao.findByBookPrice(price1,price2);	
			 System.out.println(products0.size());
		} if(products0.size()==0){ 
			//根据类别找到商品并封装
			products0=productDao.findByBookCategory(categoryId);	
		} if(products0.size()==0){ //三种情况都找不到
			System.out.println("很遗憾,没有找到");
			request.getRequestDispatcher("productCategory.jsp").forward(request, response);			
		}else{	
			//将集合设置到request内置对象中,并转发到jsp页面
			request.setAttribute("products0",products0);
			request.getRequestDispatcher("productCategory.jsp").forward(request, response);					
	}	
}
	
	//输出List<Product>集合中的值
	public void print(List<Product> products) {
		System.out.print("List:");
		for(int i=0;i<products.size();i++) {
		System.out.println("第"+(i+1)+"个元素:"+products.get(i));
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			

		doGet(request, response);
	}
}
