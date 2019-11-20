package com.jxau.redis;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * redis缓存过滤器
 */
public class MethodCacheInterceptor implements MethodInterceptor {

    private RedisUtil redisUtil;

    //禁用缓存的类名列表
    private List<String> targetNamesList;

    //禁用缓存的方法名列表
    private List<String> methodNamesList;

    //缓存默认的过期时间
    private String defaultCacheExprieTime;

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public void setTargetNamesList(List<String> targetNamesList) {
        this.targetNamesList = targetNamesList;
    }

    public void setMethodNamesList(List<String> methodNamesList) {
        this.methodNamesList = methodNamesList;
    }

    public void setDefaultCacheExprieTime(String defaultCacheExprieTime) {
        this.defaultCacheExprieTime = defaultCacheExprieTime;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object value = null;

        String targetName = invocation.getThis().getClass().getName();

        String methodName = invocation.getMethod().getName();

        if (! isAddCache(targetName, methodName)){
            //跳过缓存返回结果
            System.out.println("不加入缓存。。。");
            return invocation.proceed();
        }

        Object[] arguments = invocation.getArguments();

        String key = getCacheKey(targetName, methodName, arguments);

        try{
            //判断是否有缓存
            if (redisUtil.exists(key)){
                System.out.println("从缓存中获取。。。");
                return redisUtil.get(key);
            }

            //写入缓存
            //不存在，执行数据库操作
            value = invocation.proceed();
            if (value != null){
                final String tKey = key;
                final Object tValue = value;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //将查询的对象存入redis缓存
                        System.out.println("存入缓存。。。");
                        redisUtil.set(tKey, tValue, Long.parseLong(defaultCacheExprieTime));
                    }
                }).start();
            }
        }catch (Exception e){
            e.printStackTrace();
            if (value == null)
                return invocation.proceed();
        }

        return value;
    }

    //创建缓存key
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append("_").append(methodName);
        if ((arguments != null) && (arguments.length != 0)){
            for (int i = 0; i < arguments.length; i++) {
                sb.append("_").append(arguments[i]);
            }
        }

        return sb.toString();
    }


    //是否加入缓存
    private boolean isAddCache(String targetName, String methodName) {
        if(targetNamesList.contains(targetName) || targetName.contains("EnhancerBySpringCGLIB")){
            redisUtil.deleteKeys("*");
            return false;
        }
        for (String methName : methodNamesList) {
            if (methodName.contains(methName)){
                redisUtil.deleteKeys("*");
                return false;
            }
        }

        return true;
    }
}
