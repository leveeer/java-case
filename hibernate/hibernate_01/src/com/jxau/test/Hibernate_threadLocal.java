package com.jxau.test;

import com.jxau.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

/**
 * @ClassName Hibernate_threadLocal
 * @Description 验证Session和线程绑定的配置
 * @Author xie
 * @Date 2019/8/18 21:22
 * @Version 1.0
 */
public class Hibernate_threadLocal {
    @Test
    public void test(){
        Session openSession1 = HibernateUtils.openSession();
        Session openSession2 = HibernateUtils.openSession();
        System.out.println(openSession1 == openSession2);  //false,使用openSession方法得到的session不是同一个

        Session session1 = HibernateUtils.getCurrentSession();
        Session session2 = HibernateUtils.getCurrentSession();
        System.out.println(session1 == session2);   //true
    }
}
