package com.jxau.xw.dao.impl;

import com.jxau.xw.dao.QuestionDao;
import com.jxau.xw.domain.Question;
import com.jxau.xw.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Question> findByProjectid(int id) {
        String sql = "select * from questionnaire_question where projectid = ?";
        List<Question> questions = template.query(sql, new BeanPropertyRowMapper<Question>(Question.class), id);
        return questions;
    }
}
