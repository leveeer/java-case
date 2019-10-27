package com.jxau.dao.impl;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import com.jxau.utils.DataSourceUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 09:44
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {
   QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public int register(User user) throws SQLException {
       String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        int result = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
        return result;
    }

    @Override
    public void active(String activeCode) throws SQLException {
        String sql = "update user set state = ? where code = ?";
        runner.update(sql,1,activeCode);
    }
}
