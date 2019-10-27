package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.QuestionnaireMain;
import com.jxau.xw.service.QuestionnaireMainService;
import com.jxau.xw.service.impl.QuestionnaireMainServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findQuestionnaire")
public class FindQuestionnaire extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取id
        String id = request.getParameter("id");
        //调用Service查询
        QuestionnaireMainService service = new QuestionnaireMainServiceImpl();
        QuestionnaireMain questionnaire = service.findQuestionnaireById(id);
        //将questionnaire存入request
        request.setAttribute("questionnaire",questionnaire);
        //跳转到updatequestionnaire.jsp
        request.getRequestDispatcher("/updatequestionnaire.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
