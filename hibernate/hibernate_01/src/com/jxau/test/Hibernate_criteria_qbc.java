package com.jxau.test;

import com.jxau.domain.Student;
import com.jxau.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName Hibernate_criteria_qbc
 * @Description 使用Criteria查询（使用HQL查询能实现的查询，Criteria查询也能实现），QBC: Query By Criteria
 *                  它是一种更加面向对象的查询方式，他把生成语句的过程全都融入到方法之中了
 * @Author xie
 * @Date 2019/8/18 23:15
 * @Version 1.0
 */
public class Hibernate_criteria_qbc {

    /**
     * @Author xie
     * @Description //基本查询
     * @Date  2019/8/18 23:20
     * @Param []
     * @return void
     **/
    @Test
    public void test01(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        List<Student> list = criteria.list();
        for (Student student : list) {
            System.out.println(student);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //条件查询
     * @Date  2019/8/18 23:25
     * @Param []
     * @return void
     **/
    @Test
    public void test02(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        //使用Criteria方法的add方法来添加条件
        criteria.add(Restrictions.eq("id",6L));
        List<Student> list = criteria.list();
        for (Student student : list) {
            System.out.println(student);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //排序查询
     * @Date  2019/8/18 23:30
     * @Param []
     * @return void
     **/
    @Test
    public void test03(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        //添加排序
        //criteria.addOrder(Order.asc("id"));//升序
        criteria.addOrder(Order.desc("id"));//降序
        List<Student> list = criteria.list();
        for (Student student : list) {
            System.out.println(student);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //分页查询（QBC的分页查询和HQL的分页查询所使用的方法和方法的含义都是一模一样的）
     * @Date  2019/8/18 23:33
     * @Param []
     * @return void
     **/
    @Test
    public void test04(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        //设置分页条件
        criteria.setFirstResult(0);
        criteria.setMaxResults(3);
        List<Student> list = criteria.list();
        for (Student student : list) {
            System.out.println(student);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //统计（投影）查询
     * @Date  2019/8/18 23:36
     * @Param []
     * @return void
     **/
    @Test
    public void test05(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class);
        //设置使用聚合函数
        criteria.setProjection(Projections.rowCount());
        Long result = (Long) criteria.uniqueResult();
        System.out.println(result);
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //离线查询：DetachedCriteria对象，该对象的获取不需要Session,可以直接得到，使用此对象实现的查询，称之为离线查询
     * @Date  2019/8/18 23:43
     * @Param []
     * @return void
     **/
    @Test
    public void testServlet(){
        //获取离线对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
        //封装查询条件
        detachedCriteria.add(Restrictions.like("name","%李%"));
        List<Student> students = testService(detachedCriteria);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * @Author xie
     * @Description //模拟Service层
     * @Date  2019/8/19 00:08
     * @Param [detachedCriteria]
     * @return java.util.List<com.jxau.domain.Student>
     **/
    private List<Student> testService(DetachedCriteria detachedCriteria) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = HibernateUtils.getCurrentSession();
            transaction = session.beginTransaction();
            List<Student> students = testDao(detachedCriteria);
            transaction.commit();
            return students;
        }catch (Exception e){
            transaction.rollback();
        }
        return null;
    }

    /**
     * @Author xie
     * @Description //模拟Dao层
     * @Date  2019/8/19 00:08
     * @Param [detachedCriteria]
     * @return java.util.List<com.jxau.domain.Student>
     **/
    private List<Student> testDao(DetachedCriteria detachedCriteria) {
        Session session = HibernateUtils.getCurrentSession();
        //把离线对象转换为在线对象
        Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
        return executableCriteria.list();
    }

}
