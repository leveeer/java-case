package com.jxau.test;

import com.jxau.domain.Student;
import com.jxau.domain.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @ClassName Test
 * @Description 测试hibernate
 * @Author xie
 * @Date 2019/8/16 09:48
 * @Version 1.0
 */
public class HibernateTest {

    @Test
    public void Test01(){
        Student student = new Student();
        student.setAge(20);
        student.setName("王五");
        //1、解析配置文件
        Configuration configuration = new Configuration();
        configuration.configure();
        //2、创建SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();
        //3、创建Session对象
        Session session = factory.openSession();
        //4、开启事务
        Transaction transaction = session.beginTransaction();
        //5、执行操作
        session.save(student);
        //6、提交事务
        transaction.commit();
        //7、释放资源
        session.close();
        factory.close();

    }

    @Test
    public void test02(){
        Teacher teacher = new Teacher();
        teacher.setTname("波波");
        //1、解析配置文件
        Configuration configuration = new Configuration();
        configuration.configure();
        //2、创建SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();
        //3、创建Session对象
        Session session = factory.openSession();
        //4、开启事务
        Transaction transaction = session.beginTransaction();
        //5、执行操作
        session.save(teacher);
        //6、提交事务
        transaction.commit();
        //7、释放资源
        session.close();
        factory.close();
    }

}
