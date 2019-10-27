package com.jxau.dao.impl;

import com.jxau.dao.AdminDao;
import com.jxau.entity.*;
import com.jxau.util.DruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());
    @Override
    public Admin findAdminByNameAndPassword(String name, String password) {
        String sql = "select * from admin where name = ? and password = ?";
        try{
            Admin admin = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), name, password);
            return admin;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<Student> findAllStudentInfo() {
        String sql = "select * from student";
        try{
            List<Student> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delStudentById(int id) {
        String sql = "delete from student where id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Student finStudentById(int id) {
        String sql = "select * from student where id = ?";
        try{
            Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), id);
            return student;
        }catch(Exception e){
            return null;
        }

    }

    @Override
    public void updateStudentById(Student student) {
        String sql = "update student set name = ?, password = ?, sex = ?, email = ? where id = ?";
        jdbcTemplate.update(sql,student.getName(),student.getPassword(),student.getSex(),student.getEmail(),student.getId());
    }

    @Override
    public void addStudent(Student student) {
        String sql = "insert into student values(null,?,?,?,?)";
        jdbcTemplate.update(sql,student.getName(),student.getPassword(),student.getSex(),student.getEmail());
    }

    @Override
    public List<Grade> findAllStudentGrade() {
        String sql = "select s.name,g.stu_id,g.grade,c.class_id,c.class_name,c.teacher,c.score from (student s left join grade g on s.id = g.stu_id) left join course c on g.class_id = c.class_id";
        try {
            List<Grade> grades = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Grade>(Grade.class));
            for (Grade grade : grades) {
                System.out.println(grade);
            }
            return grades;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public void gradeDel(int id, int class_id) {
        String sql = "delete from grade where stu_id = ? and class_id = ?";
        jdbcTemplate.update(sql ,id, class_id);
    }

    @Override
    public List<Course> findAllCourse() {
        String sql = "select * from course";
        try {
            List<Course> courses = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Course>(Course.class));
            return courses;
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public Student findStudentByName(String name) {
        String sql = "select * from student where name = ?";
        try {
            Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), name);
            return student;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void studentGradeAdd(int id, int class_id, double grade) {
        String sql = "insert into grade values(null,?,?,?)";
        jdbcTemplate.update(sql,id,class_id,grade);
    }

    @Override
    public List<Announce> findAllAnnounce() {
        String sql = "select * from announce";
        try {
            List<Announce> announces = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Announce>(Announce.class));
            return announces;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addAnnounce(String head, String text, Timestamp time) {
        String sql = "insert into announce values(null,?,?,?)";
        jdbcTemplate.update(sql,head,text,time);
    }

    @Override
    public Announce findAnnounceById(int id) {
        String sql = "select * from announce where id = ?";
        try {
            Announce announce = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Announce>(Announce.class), id);
            return announce;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delAnnounce(int id) {
        String sql = "delete from announce where id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateAnnounce(Announce announce) {
        String sql = "update announce set head = ?, text = ? where id = ?";
        jdbcTemplate.update(sql, announce.getHead(), announce.getText(), announce.getId());
    }

    @Override
    public Course findCourseById(int id) {
        String sql = "select * from course where class_id = ?";
        try {
            Course course = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Course>(Course.class), id);
            return course;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Student findStudentById(int id) {
        String sql = "select * from student where id = ?";
        try {
            Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), id);
            return student;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Grade findGradeByStuIdAndClassId(int stu_id, int class_id) {
        String sql = "select * from grade where stu_id = ? and class_id = ?";
        try {
            Grade grade = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Grade>(Grade.class), stu_id, class_id);
            return grade;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateStudentGrade(int stu_id, int class_id, double grade) {
        String sql = "update grade set grade = ? where stu_id = ? and class_id = ?";
        jdbcTemplate.update(sql,grade,stu_id,class_id);
    }

    @Override
    public List<Admin> findAllAdmin() {
        String sql = "select * from admin";
        try {
            List<Admin> admins = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Admin>(Admin.class));
            return admins;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updatePassword(int id, String password) {
        String sql = "update admin set password = ? where id = ?";
        jdbcTemplate.update(sql,password,id);
    }
}
