package com.jxau.dao.impl;

import java.util.List;
import com.jxau.dao.LogDao;
import com.jxau.domain.Log;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class LogDaoImpl extends HibernateDaoSupport implements LogDao {


    @Override
    public List<Log> findRecordById(int id) {
        try {
            Query query = this.currentSession().createQuery("from Log where uid = :uid");
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
        this.getHibernateTemplate().save(log);
    }

    @Override
    public Long getTotalLogById(int id) {
        Query query = this.currentSession().createQuery("select count(*) from Log where uid = :id");
        query.setParameter("id",id);
        Long count = (Long) query.uniqueResult();

        return count;
    }

    @Override
    public List<Log> findLogByPage(int id, int index, int currentCount) {
        Session session = this.currentSession();
        Query query = session.createQuery("from Log where uid = :id");
        query.setParameter("id",id);
        query.setFirstResult(index);
        query.setMaxResults(currentCount);
        List<Log> list = query.list();
        return list;
    }
}


