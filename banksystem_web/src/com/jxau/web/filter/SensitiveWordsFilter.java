package com.jxau.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词汇过滤器
 */
//@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //创建代理对象,增强getParameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if(method.getName().contains("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String)(method.invoke(req,args));
                    if(value != null){
                        for (String str : list) {
                            if(value.contains(str)){
                                request.setAttribute("login_error","非法文字！");
                                request.getRequestDispatcher("/login.jsp").forward(request,response);
                                value = value.replaceAll(str,"***");
                            }
                        }
                    }

                    return value;
                }
                return method.invoke(req,args);
            }
        });

        //放行
        chain.doFilter(proxy_req, resp);
    }

    private List<String> list = new ArrayList<>();//敏感词汇集合
    public void init(FilterConfig config) throws ServletException {
        try {
            //获取文件的真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");

            //读取文件
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(realPath), "utf-8"));

            //将文件的每一行数据添加到List集合中
            String line = null;
            while ((line = reader.readLine()) != null){
                list.add(line);
            }
            reader.close();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
