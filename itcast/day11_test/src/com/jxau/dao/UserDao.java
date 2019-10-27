package com.jxau.dao;

import com.jxau.domain.User;
import com.jxau.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中user表的类
 */
public class UserDao{

    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 登录方法
     * @param loginUser  只有用户名和密码
     * @return  User 包含用户全部数据,没有查询到返回null
     */
    public User login(User loginUser){
        try {
            //编写sql
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记入日志
            return null;
        }
    }

}
