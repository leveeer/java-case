package com.jxau.dao;

import com.jxau.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的Dao
 */
public interface UserDao {
    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username,String password);

    void add(User user);


    void delete(int i);

    User findById(int i);

    void update(User user);

    /**
     * 查询总记录数
     * @return
     * @param condiction
     */
    int findTotalCount(Map<String, String[]> condiction);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condiction
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condiction);
}
