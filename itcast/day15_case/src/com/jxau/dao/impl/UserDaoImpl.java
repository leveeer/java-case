package com.jxau.dao.impl;

import com.jxau.dao.UserDao;
import com.jxau.domain.User;
import com.jxau.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
     JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {

        //操作数据库
        //定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            //定义sql语句
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        //定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());

    }

    @Override
    public void delete(int id) {
        //定义sql
        String sql = "delete from user where id = ?";
        template.update(sql,id);
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condiction) {
        //定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1  ";
        StringBuilder sb = new StringBuilder(sql);

        //遍历map
        Set<String> keySet = condiction.keySet();
        //定义一个参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condiction.get(key)[0];//每个查询框永远只有一个值
            //判断value是否有值
            if(value != null && !" ".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//加?条件的值
            }

        }
        System.out.println(sb.toString());
        System.out.println(params);

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condiction) {
        String sql = "select * from user where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        //遍历map
        Set<String> keySet = condiction.keySet();
        //定义一个参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condiction.get(key)[0];//每个查询框永远只有一个值
            //判断value是否有值
            if(value != null && !" ".equals(value)){
                //有值
                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");//加?条件的值
            }

        }

        //添加分页的查询
        sb.append(" limit ?,? ");

        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        //sql = sb.toString();
        System.out.println(sb.toString());
        System.out.println(params);


        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
