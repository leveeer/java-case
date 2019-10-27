package com.jxau.xw.web.sevlet;

import com.jxau.xw.service.QuestionnaireMainService;
import com.jxau.xw.service.impl.QuestionnaireMainServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteQuestionnaireServlet")
public class DeleteQuestionnaireServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //获取id
        String id = request.getParameter("id");
        //调用Service删除
        QuestionnaireMainService service = new QuestionnaireMainServiceImpl();
        service.deleteQuestionnaire(id);
        //跳转查询所有的页面
        response.sendRedirect(request.getContextPath()+"/questionnaireListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
