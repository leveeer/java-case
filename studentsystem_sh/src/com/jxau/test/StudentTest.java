package com.jxau.test;

import com.jxau.util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class StudentTest {
    @Test
    public void testJoin(){
        Session session = HibernateUtils.openSession();
        Query query = session.createQuery("select s.id, s.name, c.class_id, c.class_name, c.teacher, c.score, g.grade from Grade g join g.student s join g.course c");
        List list = query.list();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object[] objs = (Object[]) iterator.next();
            for (Object obj : objs) {
                System.out.println(obj);
            }
        }
    }
}
