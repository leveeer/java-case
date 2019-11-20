package com.jxau.wmx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jxau.wmx.dao.IProductDao;
import com.jxau.wmx.entity.Cart;
import com.jxau.wmx.entity.CartItem;
import com.jxau.wmx.entity.Product;
import com.jxau.wmx.factory.FactoryBean;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductDao productDao;

	public CartServlet() {
		super();
		try {
			productDao = FactoryBean.getInstance("productimpl", IProductDao.class);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("addCart.do")) {
			addCart(request, response);
		} else if (url.contains("removeCart.do")) {
			removeCart(request, response);
		} else if (url.contains("clearCart.do")) {
			clearCart(request, response);
		}

	}

	public Cart getCart(HttpSession session) {
		// 1、确认是否有购物车
	Cart cart = (Cart) session.getAttribute("cart");
	if (cart == null) {
	cart = new Cart();
	session.setAttribute("cart", cart);
	}
	return cart;
	}
	protected void addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 2、往购物车中加入商品
		Cart cart = getCart(session);
		String[] ids = request.getParameterValues("bookId");
		for (int i = 0; i < ids.length; i++) {
			// 获取每个商品的id
			int pid = Integer.parseInt(ids[i]);
			Product product = productDao.findById(pid);
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCount(1);
			cart.addCart(cartItem);
		}
		// 登录和购物车都是使用重定向
		response.sendRedirect("shopping.jsp");

	}

	protected void removeCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = getCart(session);
		//需要修改shopping.jsp页面的删除超链接，传递一个pid过来
		int pid = Integer.parseInt(request.getParameter("pid"));
		// 调用购物车删除购物项
		cart.removeCart(pid);
		//返回购物车页面
		request.getRequestDispatcher("shopping.jsp").forward(request, response);
	}

	protected void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//获取购物车
		Cart cart = getCart(session);
		// 清除购物车
		cart.clearCart();
		//返回购物车页面
		request.getRequestDispatcher("shopping.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
