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

@WebServlet("/findQuestionnaireByIsuseServlet")
public class FindQuestionnaireByIsuseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用Service查询
        QuestionnaireMainService service = new QuestionnaireMainServiceImpl();
        List<QuestionnaireMain> isUseQuestionnaire = service.findQuestionnaireByIsuUse();
        //将结果存入request域
        request.setAttribute("isUseQuestionnaire",isUseQuestionnaire);
        //返回问卷查看页面questionnairelist.jsp
        request.getRequestDispatcher("/questionnairelist.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
