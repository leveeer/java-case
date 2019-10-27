package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.QuestionnaireMain;
import com.jxau.xw.service.QuestionnaireMainService;
import com.jxau.xw.service.impl.QuestionnaireMainServiceImpl;
import com.jxau.xw.utils.MyBeanUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/addQuestionnaireServlet")
public class AddQuestionnaireServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> map = request.getParameterMap();

        /*for (Map.Entry<String,String[]>entry : map.entrySet()){
            System.out.println("key = "+entry.getKey()+"  value = "+entry.getValue());
        }*/
        //封装数据
        QuestionnaireMain questionnaireMain = new QuestionnaireMain();
        MyBeanUtils.populate(questionnaireMain,map);
        //调用Service添加
        QuestionnaireMainService service = new QuestionnaireMainServiceImpl();
        service.addQuestionnaire(questionnaireMain);
        //跳转回QuestionnaireListServlet
        response.sendRedirect(request.getContextPath()+"/questionnaireListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
