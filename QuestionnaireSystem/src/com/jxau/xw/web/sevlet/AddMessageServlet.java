package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.Message;
import com.jxau.xw.service.MessageService;
import com.jxau.xw.service.impl.MessageServiceImpl;
import com.jxau.xw.utils.MyBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/addMessageServlet")
public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        Message message = new Message();
        //封装数据
        MyBeanUtils.populate(message,map);
        //调用service完成插入
        MessageService service = new MessageServiceImpl();
        service.addMessage(message);
        //跳转
        response.sendRedirect(request.getContextPath()+"/findAllMessageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
