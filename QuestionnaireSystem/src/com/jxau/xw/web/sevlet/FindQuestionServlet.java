package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.Question;
import com.jxau.xw.service.QuestionService;
import com.jxau.xw.service.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findQuestionServlet")
public class FindQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取id
        String id = request.getParameter("id");
        //调用service查询
        QuestionService service = new QuestionServiceImpl();
        List<Question> questions = service.findQuestionByProjectid(id);
        //将questions存入request
        request.setAttribute("questions",questions);
        //转发到question.jsp
        request.getRequestDispatcher("question.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
