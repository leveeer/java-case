package com.jxau.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Client {

    /**
     * 基于子类的动态代理
     *      涉及的类：Enhancer
     *      提供者:第三方cglib库
     * 如何创建代理对象：
     *      使用Enhancer类中的create方法
     * 创建代理对象的要求：
     *      被代理类不能是最终类
     * create方法的参数：
     *      Class:字节码，它是用于指定被代理对象的字节码
     *      Callback:用于提供增强的代码，一般写的都是该接口的子接口实现类：MethodInterceptor
     * @param args
     */
    public static void main(String[] args) {
        final Producer producer = new Producer();

        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param obj
             * @param method
             * @param args
             * 以上三个参数和基于接口的动态代理中invoke方法的参数是一样的
             * @param proxy:当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                //提供增强的代码
                Object returnValue = null;

                //获取方法执行的参数
                Float money = (Float)args [0];
                //判断当前方法是不是销售
                if ("saleProduct".equals(method.getName())){
                    returnValue = method.invoke(producer,money * 0.8f);
                }
                return returnValue;
            }
        });
        cglibProducer.saleProduct(12000f);
    }
}
