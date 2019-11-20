package com.jxau.test;

import com.jxau.domain.Student;
import com.jxau.domain.Teacher;
import com.jxau.utils.HibernateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName Test
 * @Description 测试hibernate的增删改查
 * @Author xie
 * @Date 2019/8/16 09:48
 * @Version 1.0
 */
public class HibernateTest_crud {

    @Test
    public void testSave(){

        Student s = new Student();
        s.setName("波波");
        Session session = HibernateUtils.openSession();
        Transaction ts = session.beginTransaction();
        session.save(s);
        ts.commit();
        session.close();
    }


    /**
     * @Author xie
     * @Description  get方法和load方法的不同
     *      get方法：get(Class clazz, Serializable id)
     *      load方法：load(Class clazz, Serializable id)
     *       共同点：
     *              都是根据id查询一个实体
     *        不同点：
     *              1、查询时机不同
     *                  get：每次调用get方法时，马上发起查询      （立即加载）
     *                  load：真正使用要查询的对象的时候发起查询  （延时加载、懒加载）
     *              2、返回的结果不一样
     *                  get：返回的对象是实体类类型
     *                  load：返回的对象是实体类类型的代理对象
     *          load方法默认情况下是延时加载，可以通过配置的方式改为立即加载
     *
     * @Date  2019/8/16 21:28
     * @Param []
     * @return void
     **/

    @Test
    public void testFindOne1(){
        Session session = HibernateUtils.openSession();
        Transaction ts = session.beginTransaction();
        Student student = session.load(Student.class, 3L);
        System.out.println(student);
        ts.commit();
        session.close();
    }

    @Test
    public void testFindOne2(){
        Session session = HibernateUtils.openSession();
        Transaction ts = session.beginTransaction();
        Student student = session.get(Student.class, 2L);
        System.out.println(student);
        ts.commit();
        session.close();
    }

    @Test
    public void testUpdate(){
        Session session = HibernateUtils.openSession();
        Transaction ts = session.beginTransaction();
        Student student = session.get(Student.class, 2L);
        student.setName("xxx");
        session.saveOrUpdate(student);
        ts.commit();
        session.close();

    }

    @Test
    public void testDelete(){

        Session session = HibernateUtils.openSession();
        Transaction ts = session.beginTransaction();
        Student student = session.load(Student.class, 2L);
        session.delete(student);
        ts.commit();
        session.close();
    }

    @Test
    public void testFindAll(){
        Session session = HibernateUtils.openSession();
        Transaction ts = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("select * from student");
        List<Object[]> list = sqlQuery.list();
        for (Object[] objects : list) {
            //System.out.println(objects);
            for (Object object : objects) {
                System.out.println(object);
            }
        }
        ts.commit();
        session.close();
    }



}
