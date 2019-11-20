package com.jxau.web.servlet;

import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.service.impl.UserServiceImpl;
import com.jxau.util.BaseServlet;
import com.jxau.util.DateUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ChatServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User userLogin = service.findUserByUsernameAndPassword(user);

        if (userLogin != null){
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute("userLogin",userLogin);
            Map<String, HttpSession> userMap = (Map<String, HttpSession>) request.getServletContext().getAttribute("userMap");
            userMap.put(userLogin.getUsername(),session);
            ServletContext application = getServletContext();
            String message = "";
            message = "系统公告：" + userLogin.getUsername() + "进入聊天室" + "<br>";
            application.setAttribute("message",message);
            response.sendRedirect(request.getContextPath()+ "/index.jsp");
        }else {
            //登陆失败
            request.setAttribute("login_error","用户名或密码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    /**
     * 发送消息
     * @param request
     * @param response
     * @throws IOException
     */
    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = request.getParameter("message");
        String to = request.getParameter("to");
        String color = request.getParameter("color");
        String font = request.getParameter("font");
        String fontsize = request.getParameter("fontsize");

        User user = (User) request.getSession().getAttribute("userLogin");
        String sendMsgUser = user.getUsername();
       /* System.out.println(message);
        System.out.println(to);
        System.out.println(color);
        System.out.println(font);
        System.out.println(fontsize);*/
        if ("".equals(to)){
            String publicMessage = "";
            ServletContext application = getServletContext();
            String oldPublicMsg = (String) application.getAttribute("publicMessage");
            if (oldPublicMsg == null){
                publicMessage += "[" + DateUtils.getCurrentDate() + "]"
                        + "<span style=\"color: red\">" + sendMsgUser + "</span>"
                        + "发送群体消息：<br>" + "<span style=\"color: " + color + ";font-family :"
                        + font + ";font-size :" + fontsize +"\">"
                        + message + "</span>" + "<br>";
            }else {
                publicMessage += oldPublicMsg + "[" + DateUtils.getCurrentDate() + "]"
                        + "<span style=\"color: red\">" + sendMsgUser + "</span>"
                        + "发送群体消息：<br>" + "<span style=\"color: " + color + ";font-family :"
                        + font + ";font-size :" + fontsize +"\">"
                        + message + "</span>" + "<br>";
            }
            application.setAttribute("publicMessage",publicMessage);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else{
            String toPrivateMsg = "";
            String fromPrivateMsg = "";
            ServletContext application = getServletContext();
            Map<String, String> toUserMsgMap = (Map<String, String>) application.getAttribute("toUserMsgMap");
            Map<String, String> fromUserMsgMap = (Map<String, String>) application.getAttribute("fromUserMsgMap");
            String oldMsg = toUserMsgMap.get(to);
            String oldSendMsg = fromUserMsgMap.get(sendMsgUser);
            /*System.out.println(oldMsg);
            System.out.println(oldSendMsg);*/
            if (oldMsg == null || oldSendMsg == null){
                toPrivateMsg += "[" + DateUtils.getCurrentDate() + "]"
                        + "<span style=\"color: red\">" + sendMsgUser + "</span>"
                        + "对你说：<br>" + "<span style=\"color: " + color + ";font-family :"
                        + font + ";font-size :" + fontsize +"\">"
                        + message + "</span>" + "<br>";
                fromPrivateMsg += "[" + DateUtils.getCurrentDate() + "]"
                        + "你对" + "<span style=\"color: red\">" + to + "</span>"
                        + "说：<br>" + "<span style=\"color: " + color + ";font-family :"
                        + font + ";font-size :" + fontsize +"\">"
                        + message + "</span>" + "<br>";
            }else {
                toPrivateMsg += oldMsg + "[" + DateUtils.getCurrentDate() + "]"
                        + "<span style=\"color: red\">" + sendMsgUser + "</span>"
                        + "对你说：<br>" + "<span style=\"color: " + color + ";font-family :"
                        + font + ";font-size :" + fontsize +"\">"
                        + message + "</span>" + "<br>";
                fromPrivateMsg += oldSendMsg + "[" + DateUtils.getCurrentDate() + "]"
                        + "你对" + "<span style=\"color: red\">" + to + "</span>"
                        + "说：<br>" + "<span style=\"color: " + color + ";font-family :"
                        + font + ";font-size :" + fontsize +"\">"
                        + message + "</span>" + "<br>";
            }

            toUserMsgMap.put(to,toPrivateMsg);
            fromUserMsgMap.put(sendMsgUser,fromPrivateMsg);

            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    /**
     * 退出
     * @param request
     * @param response
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        //System.out.println(username);
        Map<String, HttpSession> userMap = (Map<String, HttpSession>) request.getServletContext().getAttribute("userMap");
        userMap.remove(username);

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
