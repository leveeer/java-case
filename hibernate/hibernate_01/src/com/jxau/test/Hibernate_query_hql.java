package com.jxau.test;

import com.jxau.domain.Student;
import com.jxau.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName Hibernate_query
 * @Description Query对象的使用
 * @Author xie
 * @Date 2019/8/18 21:39
 * @Version 1.0
 */
public class Hibernate_query_hql {

    /**
     * @Author xie
     * @Description //基本查询
     * @Date  2019/8/18 21:51
     * @Param []
     * @return void
     **/
    @Test
    public void test01(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //获取query对象
        Query query = session.createQuery("from Student");
        //获取结果集
        List<Student> list = query.list();

        for (Student student : list){
            System.out.println(student);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //条件查询1
     * @Date  2019/8/18 22:00
     * @Param []
     * @return void
     **/
    @Test
    public void test02(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //Query query = session.createQuery("from Student where id = ?");
        Query query = session.createQuery("from Student where id = :sid"); //  ：之后不能有空格
        //query.setLong(0,6);       //参数占位符从0开始
        query.setLong("sid", 6);
        List<Student> list = query.list();
        for (Student student : list) {
            System.out.println(student);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //条件模糊查询
     * @Date  2019/8/18 22:22
     * @Param []
     * @return void
     **/
    @Test
    public void test03(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Student where name like :name");
        //query.setString("name", "%波%");
        query.setParameter("name", "%波%");
        List<Student> list = query.list();
        for (Student student : list) {
            System.out.println(list);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //排序查询
     * @Date  2019/8/18 22:41
     * @Param []
     * @return void
     **/
    @Test
    public void test04(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //Query query = session.createQuery("from Student order by id asc");  //升序asc  降序desc
        Query query = session.createQuery("from Student order by id desc");
        List<Student> list = query.list();
        for (Student student : list) {
            System.out.println(student);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //分页查询
     *           mysql的分页关键字：limit
     *                  limit的两个参数
     *                      第一个：查询的开始记录索引
     *                      第二个：每次查询的条数
     *                  hibernate提供的两个方法
     *                      setFirstResult:设置查询的开始记录索引
     *                      setMaxResults:设置每次查询的条数
     *
     * @Date  2019/8/18 22:42
     * @Param []
     * @return void
     **/
    @Test
    public void test05(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Student");
        query.setFirstResult(0);
        query.setMaxResults(3);
        List<Student> list = query.list();
        for (Student student : list) {
            System.out.println(student);
        }
        transaction.commit();
    }

    /**
     * @Author xie
     * @Description //统计查询（在hql中使用聚合函数：count、sum、avg、max、min）
     * @Date  2019/8/18 22:50
     * @Param []
     * @return void
     **/
    @Test
    public void test06(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //Query query = session.createQuery("select count(*) from Student");
        Query query = session.createQuery("select max(id) from Student");
        Long result = (Long) query.uniqueResult();//当返回的结果唯一时，可以使用此方法，如果返回的结果不唯一，会抛异常
        System.out.println(result);
        transaction.commit();
    }


    /**
     * @Author xie
     * @Description //投影查询：当我们在查询实体时，只需要部分字段，而不是全部，并且希望它的返回结果使用实体类封装，而不是Object[]
     *                      这个时候我们称之为创建实体类的投影
     *                      投影查询的用法：
     *                              查询语句需要使用new关键字
     * @Date  2019/8/18 22:58
     * @Param []
     * @return void
     **/
    @Test
    public void test07(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select new com.jxau.domain.Student(id, name) from Student");//需要在实体类中提供对应参数的构造函数
        List<Student> list = query.list();
        for (Student student : list) {
            System.out.println(student);
        }
        transaction.commit();
    }




}
