package service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceImplJDKProxy implements InvocationHandler {

    //定义目标对象
    private Object target;

    public Object newProxy(Object target){
        this.target = target;
        System.out.println(this);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().contains("add") || method.getName().contains("delete")){
            check();
            Object invoke = method.invoke(this.target, args);
            writeLog();
            return invoke;
        }else {
            return method.invoke(this.target,args);
        }

    }

    private void writeLog() {
        System.out.println("记录日志");
    }

    private void check() {
        System.out.println("用户身份验证");
    }
}
