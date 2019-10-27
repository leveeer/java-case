package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.QuestionnaireMain;
import com.jxau.xw.service.QuestionnaireMainService;
import com.jxau.xw.service.impl.QuestionnaireMainServiceImpl;
import com.jxau.xw.utils.MyBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/updateQuestionnaireServlet")
public class UpdateQuestionnaireServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        QuestionnaireMain questionnaireMain = new QuestionnaireMain();
        //封装数据
        MyBeanUtils.populate(questionnaireMain,map);
        //调用Service修改
        QuestionnaireMainService service = new QuestionnaireMainServiceImpl();
        service.updateQuestionnaire(questionnaireMain);
        //跳转问卷信息页面
        response.sendRedirect(request.getContextPath()+"/questionnaireListServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
