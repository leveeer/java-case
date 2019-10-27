package com.jxau.wmx.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jxau.wmx.dao.IOrderDao;
import com.jxau.wmx.dao.IOrderItemDao;
import com.jxau.wmx.entity.Cart;
import com.jxau.wmx.entity.CartItem;
import com.jxau.wmx.entity.Order;
import com.jxau.wmx.entity.OrderItem;
import com.jxau.wmx.entity.User;
import com.jxau.wmx.factory.FactoryBean;

/**
 * ???servlet??????????????§Õ??
 * @author Administrator
 *
 */

@WebServlet(urlPatterns= {"/createorder.do","/orderlist.do"})
public class OrderServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
    private IOrderDao orderDao;
    private IOrderItemDao orderItemDao;
    
    public OrderServlet() {
        super();
        try {
			orderDao=FactoryBean.getInstance("orderdaoimpl", IOrderDao.class);
			orderItemDao=FactoryBean.getInstance("orderitemdaoimpl", IOrderItemDao.class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//???????????????
		String url=request.getRequestURL().toString();
		//???????????
		if(url.contains("createorder.do")) {
			createOrder(request, response);
		}
		//??????
		else if(url.contains("orderlist.do")) {
			orderList(request, response);
		}
		
	}

	/**
	 * ???????????
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
  protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     HttpSession session=request.getSession();
     //1???§Ø????????
	 Cart cart=(Cart) session.getAttribute("cart");	
	 if(cart==null) {
		 request.getRequestDispatcher("shopping.jsp").forward(request, response);
	 }
	 //2???§Ø???????????????????????
	 User user=(User) session.getAttribute("user");
	 if(user==null) {
		 //????????????????
		 request.getRequestDispatcher("login.jsp").forward(request, response); 
	 }
	 //?????????????????????????´Â
	 String address=request.getParameter("receiverAddress");
	 String name=request.getParameter("receiverName");
	 String phone=request.getParameter("receiverPhone");
	 //3??????????????????
	 Order order=new Order();
	 order.setOid(UUID.randomUUID().toString());
	 order.setMoney(cart.getTotal());
	 order.setAddress(address);
	 order.setName(name);
	 order.setPhone(phone);
	 order.setPaystate(1);//¦Ä????
	 order.setUser(user);
	 //????????????????
	 SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 String now= sf.format(new Date());
	 order.setOrdertime(now);
	 //???????
	 orderDao.creatOrder(order);
	 //??????????§Ø????????
	 for(CartItem cartItem:cart.getCartItems()) {
		 OrderItem orderItem=new OrderItem();
		 orderItem.setOid(order.getOid());
		 orderItem.setProduct(cartItem.getProduct());
		 orderItem.setBuynum(cartItem.getCount());
		 orderItem.setSubTotal(cartItem.getSubTotal());
		 orderItemDao.saveOrderItem(orderItem);
		//????????????????
		 order.getOrderItems().add(orderItem);
	 }
	//??????
	cart.clearCart();
	//???????????????
	request.getRequestDispatcher("createorder-success.jsp").forward(request, response);
 }
	
  /**
   * ??????????????????????§Ø???
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   HttpSession session=request.getSession();
	    User user=(User) session.getAttribute("user");
	    if(user==null) {
	    	request.getRequestDispatcher("login.jsp").forward(request, response);
	    }
	    //System.out.println("????????");
	   List<Order>orders=orderDao.findOrderByUser(user);
		//System.out.println("????????§Ø?????"+orders);
		//??Orders????????Orderlist.jsp
		session.setAttribute("orders", orders);
		//??????????????
		request.getRequestDispatcher("orderlist.jsp").forward(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
