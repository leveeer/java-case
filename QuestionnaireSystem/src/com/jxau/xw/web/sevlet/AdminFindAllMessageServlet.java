package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.Message;
import com.jxau.xw.service.MessageService;
import com.jxau.xw.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminFindAllMessageServlet")
public class AdminFindAllMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用service查询
        MessageService service = new MessageServiceImpl();
        List<Message> messages = service.findAllMessage();
        //将messages存入request域
        request.setAttribute("messages",messages);
        //跳转
        request.getRequestDispatcher("/messagemanage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
