package com.jxau.dao.impl;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import com.jxau.util.HibernateFilter;
import com.jxau.util.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author xie
 * @Date 2019/8/29 20:11
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void register(User user) {
        HibernateFilter.getSession().save(user);
    }

    @Override
    public User findUserByUsernameAndPassword(User user) {
        try {
            Query query = HibernateFilter.getSession().createQuery("from User where username = :username and password = :password");
            query.setString("username", user.getUsername());
            query.setString("password", user.getPassword());
            User userLogin = (User) query.uniqueResult();
            return userLogin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addBalance(User user) {
        HibernateFilter.getSession().save(user);
    }

    @Override
    public User findUserByUsername(String getMoneyUser) {
        try {
            Query query = HibernateFilter.getSession().createQuery("from User where username = :username");
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
            Query query = HibernateFilter.getSession().createQuery("from User");
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
            Query query = HibernateFilter.getSession().createQuery("from User where uid = :uid");
            query.setInteger("uid", id);
            User user = (User) query.uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePwdById(int id, String newPwd) {
        User user = HibernateFilter.getSession().load(User.class, id);
        user.setPassword(newPwd);
        HibernateFilter.getSession().save(user);
    }

    @Override
    public Long getTotalUser() {

        Query query = HibernateFilter.getSession().createQuery("select count(*) from User");
        Long count = (Long) query.uniqueResult();
        return count;
    }

    @Override
    public List<User> findUserByPage(int index, int currentCount) {
        Query query = HibernateFilter.getSession().createQuery("from User");
        query.setFirstResult(index);
        query.setMaxResults(currentCount);
        List list = query.list();
        return list;
    }

    @Override
    public List<User> findUserByCondition(DetachedCriteria dc,int index,int currentCount) {
        Session session = HibernateFilter.getSession();
        Criteria criteria = dc.getExecutableCriteria(session);
        System.out.println(index);
        System.out.println(currentCount);
        System.out.println(dc);
        criteria.setFirstResult(index);
        criteria.setMaxResults(currentCount);
        List list = criteria.list();
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println(list);
        return list;
    }

    @Override
    public Long getUserConditionCount(DetachedCriteria dc) {
        Criteria criteria = dc.getExecutableCriteria(HibernateFilter.getSession());
        List list = criteria.list();
        System.out.println(list.size());
        return Long.parseLong(list.size() + "") ;
    }
}
