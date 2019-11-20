package com.jxau.test;

import com.jxau.util.HibernateFilter;
import com.jxau.util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class Test_Class {
    @Test
    public  void testJoin(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query query = session.createQuery("select l.currentMoney, u.username from Log l join l.user u");
            List list = query.list();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                Object[] o = (Object[]) iterator.next();
                for (Object obj : o) {
                    System.out.println(obj);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
