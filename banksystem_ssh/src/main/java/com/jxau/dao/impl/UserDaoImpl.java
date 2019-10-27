package com.jxau.dao.impl;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author xie
 * @Date 2019/8/29 20:11
 * @Version 1.0
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public void register(User user) {
        this.getHibernateTemplate().save(user);
    }

    @Override
    public User findUserByUsernameAndPassword(User user) {
        try {
            Query query = this.currentSession().createQuery("from User where username = :username and password = :password");
            query.setParameter("username", user.getUsername());
            query.setParameter("password", user.getPassword());
            User userLogin = (User) query.uniqueResult();
            return userLogin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateBalance(User user) {
        this.getHibernateTemplate().save(user);
    }

    @Override
    public User findUserByUsername(String getMoneyUser) {
        try {
            Query query = this.currentSession().createQuery("from User where username = :username");
            query.setString("username", getMoneyUser);
            User user = (User) query.uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAllUser() {
        try {
            Query query = this.currentSession().createQuery("from User");
            List list = query.list();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findUser(Map<String, String> condition) {
        return null;
    }

    @Override
    public User findUserById(int id) {
        try {
            Query query = this.currentSession().createQuery("from User where uid = :uid");
            query.setParameter("uid", id);
            User user = (User) query.uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePwdById(int id, String newPwd) {

        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        User user = hibernateTemplate.load(User.class, id);
        user.setPassword(newPwd);
        hibernateTemplate.save(user);
    }

    @Override
    public Long getTotalUser() {

        Query query = this.currentSession().createQuery("select count(*) from User");
        Long count = (Long) query.uniqueResult();
        return count;
    }

    @Override
    public List<User> findUserByPage(int index, int currentCount) {
        Query query = this.currentSession().createQuery("from User");
        query.setFirstResult(index);
        query.setMaxResults(currentCount);
        List list = query.list();
        return list;
    }

    @Override
    public List<User> findUserByCondition(DetachedCriteria dc,int index,int currentCount) {
        Session session = this.currentSession();
        Criteria criteria = dc.getExecutableCriteria(session);
        criteria.setFirstResult(index);
        criteria.setMaxResults(currentCount);
        List list = criteria.list();
        return list;
    }

    @Override
    public Long getUserConditionCount(DetachedCriteria dc) {
        Criteria criteria = dc.getExecutableCriteria(this.currentSession());
        List list = criteria.list();
        System.out.println(list.size());
        return Long.parseLong(list.size() + "") ;
    }
}
