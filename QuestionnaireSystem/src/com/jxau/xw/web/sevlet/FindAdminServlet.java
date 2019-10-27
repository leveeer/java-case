package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.Admin;
import com.jxau.xw.service.AdminService;
import com.jxau.xw.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findAdminServlet")
public class FindAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取id
        String id = request.getParameter("id");
        //调用service查询
        AdminService service = new AdminServiceImpl();
        Admin admin = service.findAdminById(id);
        //将admin存入request
        request.setAttribute("admin",admin);
        //转发到updateadminpassword.jsp
        request.getRequestDispatcher("/updateadminpassword.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
