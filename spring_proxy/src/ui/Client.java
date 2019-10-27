package ui;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;
import service.impl.UserServiceImpl;
import service.impl.UserServiceImplJDKProxy;
import service.impl.UserServiceImplProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    private ApplicationContext ac = null;
    @Before
    public void initBeanFactory(){
        ac =  new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testProxy(){
        UserServiceImplProxy userServiceImplProxy = (UserServiceImplProxy) ac.getBean("userServiceImplProxy");
        userServiceImplProxy.add("张三",18);
    }

    @Test
    public void testJDKProxy(){
        UserServiceImplJDKProxy jdkProxy = new UserServiceImplJDKProxy();
        UserService userService_proxy = (UserService) jdkProxy.newProxy(new UserServiceImpl());
        userService_proxy.add("张三", 22);
        System.out.println("================");
        userService_proxy.delete(1);
    }

    @Test
    public void testJDKProxy_01(){
        //创建目标对象
        UserServiceImpl userService = new UserServiceImpl();
        UserService proxyInstance = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().contains("add")){
                    check();
                    String name = (String) args[0];
                    //增强参数
                    int age = (int) args[1];
                    age += 1;
                    Object invoke = method.invoke(userService, name, age);
                    writeLog();
                    return invoke;
                }else if (method.getName().contains("say")){
                    //增强返回值
                    return method.invoke(userService,args) + "world";
                }else {
                    return method.invoke(userService,args);
                }

            }
        });
        proxyInstance.add("张三",28);
        System.out.println("=================");
        proxyInstance.delete(1);
        System.out.println("=================");
        String say = proxyInstance.say();
        System.out.println(say);
    }

    private void writeLog() {
        System.out.println("记录日志");
    }

    private void check() {
        System.out.println("用户身份验证");
    }
}
