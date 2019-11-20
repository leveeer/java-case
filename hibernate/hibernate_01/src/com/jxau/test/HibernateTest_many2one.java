package com.jxau.test;

import com.jxau.domain.Student;
import com.jxau.domain.Teacher;
import com.jxau.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @ClassName HibernateTest_many2one
 * @Description TODO
 * @Author xie
 * @Date 2019/8/28 19:09
 * @Version 1.0
 */
public class HibernateTest_many2one {
    @Test
    public void testManyToOne(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Teacher teacher = new Teacher();
            teacher.setTname("波波");
            teacher.setTpassword("123");
            teacher.setTclass("java开发");
            Student student = new Student();
            student.setName("王明星");
            student.setAge(18);
            student.setPassword("123456");
            student.setSex("男");
            student.setTeacher(teacher);
            session.save(student);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            if (session != null){
                if (session.isOpen()){
                    session.close();
                }
            }
        }
    }
}
