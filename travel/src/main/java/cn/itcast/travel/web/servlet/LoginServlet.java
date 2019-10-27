package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //封装user
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用Service查询
        UserService service = new UserServiceImpl();
        User u = service.login(user);
        //创建响应信息对象
        ResultInfo info = new ResultInfo();
        //判断用户对象是否为null
        if(u == null){
            //用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
        //判断用户是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活！");
        }
        //判断登录成功
        if (u != null && "Y".equals(u.getStatus())){
            info.setFlag(true);
            HttpSession session = request.getSession();
            session.setAttribute("user",u);
        }
        //响应数据
        ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(response.getOutputStream(),info);
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
