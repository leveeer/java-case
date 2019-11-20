package com.jxau.dao.impl;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import com.jxau.util.JDBCUtils;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void addBalance(int id, BigDecimal money) {

        // 定义sql
        String sql = "update user set money = ? where id = ?";

        template.update(sql, money, id);
    }

    @Override
    public List<User> findAllUser() {
        String sql = "select * from user";

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public List<User> findUser(Map<String, String> condition) {

        // 定义初始化sql
        String        sql = "select * from user where 1 = 1";
        StringBuilder sb  = new StringBuilder(sql);

        // 遍历map集合
        Set<String> keys = condition.keySet();

        // 定义一个存放参数的集合
        List<Object> params = new ArrayList<Object>();

        for (String key : keys) {

            // 获取value,可以确定每个输入框只有一个值
            String value = condition.get(key);

            // 判断value是否有值
            if ((value != null) &&!" ".equals(value)) {

                // 有值
                sb.append(" and " + key + " like ?");
                params.add("%" + value + "%");    // 加?条件的值
            }
        }

        System.out.println(sb.toString());
        System.out.println(params);

        return template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id = ?";

        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findUserByUsername(String getMoneyUser) {
        try {
            String sql  = "select * from user where username = ?";
            User   user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), getMoneyUser);

            // System.out.println(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {

        // 定义sql
        String sql = "select * from user where username = ? and password = ?";

        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);

            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void register(User user) {

        // 定义sql
        String sql = "insert into user values(null,?,?,?,?)";

        template.update(sql, user.getUsername(), user.getPassword(), user.getMoney(), user.getIsLocked());
    }

    @Override
    public void updatePwdById(int id, String newPwd) {
        String sql = "update user set password = ? where id = ?";

        template.update(sql, newPwd, id);
    }
}


