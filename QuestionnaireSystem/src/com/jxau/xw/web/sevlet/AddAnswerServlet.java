package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.Answer;
import com.jxau.xw.service.AnswerService;
import com.jxau.xw.service.impl.AnswerServiceImpl;
import com.jxau.xw.utils.MyBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/addAnswerServlet")
public class AddAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");

        String editor = request.getParameter("editor");
        String projectid = request.getParameter("projectid");
        String[] answers = request.getParameterValues("answer");
        Answer answer = new Answer();
        for (int i = 0; i < answers.length ; i++) {
            answer.setAnswer(answers[i]);
            System.out.println(answer);
        }
        answer.setEditor(editor);
        answer.setProjectid(Integer.parseInt(projectid));
        /*Map<String, String[]> map = request.getParameterMap();
        Answer answer = new Answer();
        //封装数据
        MyBeanUtils.populate(answer, map);*/
        //调用Service插入
        AnswerService service = new AnswerServiceImpl();
        service.addAnswer(answer);
        //跳转回findQuestionnaireByIsuseServlet
        request.getRequestDispatcher("/findQuestionnaireByIsuseServlet").forward(request, response);

    }


        protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request, response);

        }
    }
