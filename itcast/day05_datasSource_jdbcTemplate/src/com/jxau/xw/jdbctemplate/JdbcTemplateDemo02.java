package com.jxau.xw.jdbctemplate;

import com.jxau.xw.datasource.utils.JDBCUtils;
import com.jxau.xw.domain.Student;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo02 {
    //Junit单元测试，可以让方法独立执行

    //获取JDBCTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 修改1号数据的math为80
     */
    @Test
    public void test1() {
        /*//获取JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());*/
        //定义sql
        String sql = "update student set sex = '男' where id = ?";
        //执行sql
        int result = template.update(sql, 2);
        System.out.println(result);
    }

    /**
     * 添加一条记录
     */
    @Test
    public void test2() {
        //定义sql
        String sql = "insert into student(id,name,age) values(?,?,?)";
        //执行sql
        int result = template.update(sql, null, "王健林", 58);
        System.out.println(result);
    }

    /**
     * 删除id为1的记录
     */
    @Test
    public void test3() {
        //定义sql
        String sql = "delete from student where id = ?";
        //执行sql
        int result = template.update(sql, 11);
        System.out.println(result);
    }

    /**
     * 查询id为2的记录，将其封装为Map集合
     */
    @Test
    public void test4() {
        //定义sql
        String sql = "select * from student where id = ? ";
        //执行sql
        Map<String, Object> map = template.queryForMap(sql, 2);
        System.out.println(map);//{id=2, name=马化腾, age=45, sex=女, address=深圳, math=98, english=87}
    }

    @Test
    /**
     * 查询所有记录，将其封装为List
     */
    public void test5() {
        //定义sql
        String sql = "select * from student ";
        //执行sql
        List<Map<String, Object>> list = template.queryForList(sql);
        /*//迭代器遍历集合
        Iterator<Map<String, Object>> iterator = list.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            System.out.println(map);
        }*/
        //增强for遍历集合
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    /**
     * 查询所有记录，将其封装为Student对象的List集合
     */
    @Test
    public void test6(){
        //定义sql
        String sql = "select * from student";
        List<Student> list = template.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
                Student student = new Student();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String address = rs.getString("address");
                int math = rs.getInt("math");
                int english = rs.getInt("english");

                student.setId(id);
                student.setName(name);
                student.setAge(age);
                student.setSex(sex);
                student.setAddress(address);
                student.setMath(math);
                student.setEnglish(english);
                return student;
            }
        });

        //增强for遍历集合
        for (Student student : list) {
            System.out.println(student);
        }
        /*//迭代器遍历集合
        Iterator<Student> iterator = list.iterator();
        while(iterator.hasNext()){
            Student student = iterator.next();
            System.out.println(student);
        }*/

    }

    /**
     * 查询所有记录，将其封装为Student对象的List集合
     * 使用Spring JDBC简化代码
     */
    @Test
    public void test6_2(){
        //定义sql
        String sql = "select * from student";
        List<Student> list = template.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        //for遍历
        for (Student student : list) {
            System.out.println(student);
        }
        //迭代器遍历
        Iterator<Student> iterator = list.iterator();
        while(iterator.hasNext()){
            Student student = iterator.next();
            System.out.println(student);
        }
    }

    /**
     *  查询总记录数
     */
    @Test
    public void test7(){
        //定义sql
        String sql = "select count(id) from student ";
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }



}





