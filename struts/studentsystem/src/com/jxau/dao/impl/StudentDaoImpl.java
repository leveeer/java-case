package com.jxau.dao.impl;

import com.jxau.dao.StudentDao;
import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;
import com.jxau.util.DruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());
    @Override
    public void register(Student student) {
        String sql = "insert into student values(null,?,?,?,?)";
        jdbcTemplate.update(sql,student.getName(),student.getPassword(),student.getSex(),student.getEmail());
    }

    @Override
    public Student findByNameAndPassword(String name, String password) {
        try {
            String sql = "select * from student where name = ? and password = ?";
            Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), name, password);
            return student;
        }catch(Exception e){
            return null;
        }

    }

    @Override
    public void updateNameById(int id, String name) {
        String sql = "update student set name = ? where id = ? ";
        jdbcTemplate.update(sql,name,id);
    }

    @Override
    public Student findById(int id) {
        try {
            String sql = "select * from student where id = ?";
            Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), id);
            return student;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Grade> inquiryGradeById(int id) {
        String sql = "select s.name,g.stu_id,g.grade,c.class_name,c.teacher,c.score from (student s left join grade g on s.id = g.stu_id) left join course c on g.class_id = c.class_id where g.stu_id = ?";
        try{
            List<Grade> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Grade>(Grade.class), id);
            return list;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Announce> inquiryAnnounce() {
        String sql = "select * from announce";
        try {
            List<Announce> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Announce>(Announce.class));
            return list;
        }catch(Exception e){
            return null;
        }

    }

    @Override
    public Announce findAnnounceById(int id) {
        String sql = "select * from Announce where id = ?";
        try{
            Announce announce = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Announce>(Announce.class), id);
            return announce;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updatePasswordById(int id, String password) {
        String sql = "update student set password = ? where id = ?";
        jdbcTemplate.update(sql,password,id);
    }

}
