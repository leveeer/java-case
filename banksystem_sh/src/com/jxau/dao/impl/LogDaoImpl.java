package com.jxau.dao.impl;

import java.util.List;
import com.jxau.dao.LogDao;
import com.jxau.domain.Log;
import com.jxau.domain.User;
import com.jxau.util.HibernateFilter;
import com.jxau.util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;

public class LogDaoImpl implements LogDao {


    @Override
    public List<Log> findRecordById(int id) {
        try {
            Query query = HibernateFilter.getSession().createQuery("from Log where uid = :uid");
            query.setInteger("uid", id);
            List list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insertLog(Log log) {
        HibernateFilter.getSession().save(log);
    }

    @Override
    public Long getTotalLogById(int id) {
        Query query = HibernateFilter.getSession().createQuery("select count(*) from Log where uid = :id");
        query.setParameter("id",id);
        Long count = (Long) query.uniqueResult();

        return count;
    }

    @Override
    public List<Log> findLogByPage(int id, int index, int currentCount) {
        Session session = HibernateFilter.getSession();
        Query query = session.createQuery("from Log where uid = :id");
        query.setParameter("id",id);
        query.setFirstResult(index);
        query.setMaxResults(currentCount);
        List<Log> list = query.list();
        return list;
    }
}


