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
import java.util.List;

@WebServlet("/questionnaireListServlet")
public class QuestionnaireListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用Service查询
        QuestionnaireMainService service = new QuestionnaireMainServiceImpl();
        List<QuestionnaireMain> questionnaires = service.findAllQuestionnaire();
        //将questionnaires存入request域中
        request.setAttribute("questionnaires",questionnaires);
        //转发到questionnairemanage.jsp
        request.getRequestDispatcher("/questionnairemanage.jsp").forward(request,response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
