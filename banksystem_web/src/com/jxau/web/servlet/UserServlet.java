package com.jxau.web.servlet;
import	java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxau.domain.Log;
import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.service.impl.UserServiceImpl;
import com.jxau.util.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//@WebServlet("/userServlet/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //获取参数
        Map<String, String[]> map = request.getParameterMap();

        //封装数据
        User user = new User();
        user.setMoney(new BigDecimal("0.00"));
        user.setIsLocked(0);
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //调用Service
        userService.userRegister(user);

        //注册成功
        request.setAttribute("opera_success", "注册成功");
        request.setAttribute("dispatcher_url", "登录页面");
        request.getRequestDispatcher("/success.jsp").forward(request,response);
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //封装数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用UserService查询
        User userLogin = userService.userLogin(user);
        //判断是否登录成功
        if (userLogin != null){
            //登录成功
            //将用户信息存入session
            HttpSession session = request.getSession();
            session.setAttribute("user",userLogin);
            //跳转页面
            response.sendRedirect(request.getContextPath() + "/userIndex.jsp");
        }else {
            //登录失败
            //将错误信息存入request域
            request.setAttribute("login_error","用户名或密码错误！");
            //转发
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    /**
     * 用户存款
     * @param request
     * @param response
     */
    public void deposit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strMoney = request.getParameter("money");
        String id = request.getParameter("id");
        BigDecimal money = null;
        try {
            money = new BigDecimal(strMoney);
        }catch (NumberFormatException e){
            request.setAttribute("deposit_error","您输入的金额格式错误！");
            request.getRequestDispatcher("/deposit.jsp").forward(request, response);
        }
        if (money.compareTo(BigDecimal.ZERO) == -1) {
            request.setAttribute("deposit_error", "存款不能为负数！");
            request.getRequestDispatcher("/deposit.jsp").forward(request, response);
        } else {
            userService.deposit(money,id);
            request.setAttribute("opera_success", "存款成功");
            request.setAttribute("dispatcher_url", "首页");
            request.getRequestDispatcher("/success.jsp").forward(request, response);

        }
    }

    /**
     * 用户取款
     * @param request
     * @param response
     */
    public void withdraw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strMoney = request.getParameter("money");
        String id = request.getParameter("id");
        BigDecimal money = null;
        try {
            money = new BigDecimal(strMoney);
        }catch (NumberFormatException e) {
            request.setAttribute("withdraw_error", "您输入的金额格式错误！");
            request.getRequestDispatcher("/withdraw.jsp").forward(request, response);
        }
        if (money.compareTo(BigDecimal.ZERO) == -1) {
            request.setAttribute("withdraw_error", "取款不能为负数！");
            request.getRequestDispatcher("/withdraw.jsp").forward(request, response);
        } else {
            boolean flag = userService.withdraw(id, money);
            if (flag) {
                request.setAttribute("opera_success", "取款成功");
                request.setAttribute("dispatcher_url", "首页");
                request.getRequestDispatcher("/success.jsp").forward(request, response);
            } else {
                request.setAttribute("withdraw_error", "您的余额不足！");
                request.getRequestDispatcher("/withdraw.jsp").forward(request, response);
            }
        }
    }

    /**
     * 用户转账
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String getMoneyUser = request.getParameter("getMoneyUser");
        String strTransferMoney = request.getParameter("transferMoney");
        BigDecimal transferMoney = null;
        try {
            transferMoney = new BigDecimal(strTransferMoney);
        }catch (NumberFormatException e){
            request.setAttribute("transfer_error","您输入的金额格式有误！");
            request.getRequestDispatcher("/transfer.jsp").forward(request, response);
        }
        if (transferMoney.compareTo(BigDecimal.ZERO) == -1){
            request.setAttribute("transfer_error","转账金额不能为负");
            request.getRequestDispatcher("/transfer.jsp").forward(request, response);

        }else if (userService.findUserByUsername(getMoneyUser) == null){
            request.setAttribute("transfer_error","您输入的用户不存在！");
            request.getRequestDispatcher("/transfer.jsp").forward(request,response);
        }else {
            boolean flag = userService.transfer(id, getMoneyUser, transferMoney);
            System.out.println(flag);
            if (flag) {
                request.setAttribute("opera_success", "转账成功");
                request.setAttribute("dispatcher_url", "首页");
                request.getRequestDispatcher("/success.jsp").forward(request, response);
            }else {
                request.setAttribute("transfer_error","您的余额不足！");
                request.getRequestDispatcher("/transfer.jsp").forward(request,response);
            }
        }

    }

    /**
     * 查询明细
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void record(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        List<Log> logList = userService.findAllRecord(id);
        request.setAttribute("logList",logList);
        request.getRequestDispatcher("/record.jsp").forward(request,response);
    }


    /**
     * 用户退卡
     * @param request
     * @param response
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }else {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    /**
     * 查询余额
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void inquiry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        User user = userService.findUserByUsername(username);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/inquiry.jsp").forward(request,response);
    }

    /**
     * 验证用户是否存在
     * @param request
     * @param response
     * @throws IOException
     */
    public void findUserByUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        System.out.println(username);
        User user = userService.findUserByUsername(username);

        //设置响应的数据格式为json
        response.setContentType("application/json;charset=utf-8");

        Map<String,Object> map = new HashMap<String,Object>();

        System.out.println(user);

        if (user == null){
            //用户不存在
            map.put("userExsit",false);
            map.put("msg","<img width='35' height='25' src='img/gou.png'/>");
        }else {
            //用户存在
            map.put("userExsit",true);
            map.put("msg","该用户已存在");
        }

        //将map转为json,传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }
}
