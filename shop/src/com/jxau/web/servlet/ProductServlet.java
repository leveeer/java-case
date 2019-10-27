package com.jxau.web.servlet;

import com.google.gson.Gson;
import com.jxau.domain.*;
import com.jxau.service.CategoryService;
import com.jxau.service.ProductService;
import com.jxau.service.impl.CategoryServiceImpl;
import com.jxau.service.impl.ProductServiceImpl;
import com.jxau.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ProductServlet extends BaseServlet {

    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        List<Product> hotProducts = productService.findHotProduct();
        List<Product> newProducts = productService.findNewProduct();
        List<Category> category = categoryService.findAllCategory();

        request.setAttribute("hotProducts",hotProducts);
        request.setAttribute("newProducts",newProducts);
        request.setAttribute("category",category);
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }

    public void category(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        List<Category> category = null;
        //先从缓存中查询category，如果有直接使用，没有再从数据库中查询存入缓存
        //获得jedis对象 连接redis数据库
        Jedis jedis = JedisPoolUtils.getJedis();
        String categoryJson = jedis.get("categoryJson");
        //判断categoryJson是否为空
        if (categoryJson == null){

            category = categoryService.findAllCategory();
            Gson gson = new Gson();
            categoryJson = gson.toJson(category);
            jedis.set("categoryJson",categoryJson);
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(categoryJson);
    }

    public void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String currentPage = request.getParameter("currentPage");
        String cid = request.getParameter("cid");
        String pid = request.getParameter("pid");

        Category category = categoryService.findCategoryById(cid);
        Product product =  productService.findProductById(pid);

        request.setAttribute("categoryInfo",category);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("product",product);


        //获取客户端携带的cookie
        String pids = pid;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if ("pids".equals(cookie.getName())){
                    pids = cookie.getValue();
                    //将pids拆分成数组
                    String[] split = pids.split("-");
                    List<String> strings = Arrays.asList(split);
                    LinkedList<String> list = new LinkedList<String>(strings);
                    //判断集合中是否存在当前pid
                    if (list.contains(pid)){
                        //包含
                        list.remove(pid);
                        list.addFirst(pid);
                    }else{
                        list.addFirst(pid);
                    }

                    //转换
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < list.size() && i < 7; i++) {
                        sb.append(list.get(i));
                        sb.append("-");
                    }
                    pids = sb.substring(0,sb.length() - 1);
                }
            }
        }
        Cookie cookie_pids = new Cookie("pids",pids);
        response.addCookie(cookie_pids);
        request.getRequestDispatcher("/product_info.jsp").forward(request,response);
    }

    public void productsByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String cid = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr == null){
            currentPageStr = "1";
        }
        int currentPage = Integer.parseInt(currentPageStr);
        int currentCount = 12;

        PageBean pageBean = productService.findCategoryById(cid,currentPage,currentCount);
        request.setAttribute("pageBean",pageBean);
        request.setAttribute("cid",cid);

        //定义一个记录历史信息的集合
        ArrayList<Product> historyProducts = new ArrayList<>();

        //获取客户端携带名字叫pids的cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if ("pids".equals(cookie.getName())){
                    String pids = cookie.getValue();
                    String[] split = pids.split("-");
                    for (String s : split) {
                        Product pro = productService.findProductById(s);
                        historyProducts.add(pro);
                    }
                }
            }
        }

        //将历史记录集合添加到域中
        request.setAttribute("historyProducts",historyProducts);

        request.getRequestDispatcher("/product_list.jsp").forward(request,response);
    }

    public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String pid = request.getParameter("pid");
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));

        //获取product对象
        Product product = productService.findProductById(pid);
        //计算小计
        double subtotal = product.getShop_price() * buyNum;
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setBuyNum(buyNum);
        cartItem.setSubtotal(subtotal);
        //获得购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
        }
        //先判断购物车是否包含此购物项
        Map<String, CartItem> cartItems = cart.getCartItems();
        if (cartItems.containsKey(pid)){
            CartItem item = cartItems.get(pid);
            int oidBuyNum = item.getBuyNum();
            int newBuyNum = oidBuyNum + buyNum;
            item.setBuyNum(newBuyNum);

            double oldSubtotal = cartItem.getSubtotal();
            double newSubtoal = buyNum * product.getShop_price();
            cartItem.setSubtotal(oldSubtotal + newSubtoal);
            cart.getCartItems().put(pid,cartItem);
        }else {
            //将购物项放入购物车
            cart.getCartItems().put(pid,cartItem);
        }

        //计算购物车商品总金额
        double total = cart.getTotal() + subtotal;
        cart.setTotal(total);
        //将购物车放回session
        request.getSession().setAttribute("cart",cart);
        //重定向，转发刷新一次会重新发送一次请求，再次购买商品，购物车必须使用重定向
        response.sendRedirect(request.getContextPath() + "/cart.jsp");


    }
}
