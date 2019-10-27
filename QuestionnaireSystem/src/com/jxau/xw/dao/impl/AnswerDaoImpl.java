package com.jxau.xw.dao.impl;

import com.jxau.xw.dao.AnswerDao;
import com.jxau.xw.domain.Answer;
import com.jxau.xw.utils.JDBCUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public void add(Answer answer) {
       /* final List<Answer> answerList = new ArrayList<>();
        answerList.add(answer);*/
        String sql = "insert into questionnaire_answer values(null,?,?,now(),?)";
        template.update(sql,answer.getAnswer(),answer.getEditor(),answer.getProjectid());
       /* //批量添加
        template.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String answer = answerList.get(i).getAnswer();
                String editor = answerList.get(i).getEditor();
                int projectid = answerList.get(i).getProjectid();
                ps.setString(1,answer);
                ps.setString(2,editor);
                ps.setInt(3,projectid);
            }
            @Override
            public int getBatchSize() {
                return answerList.size();
            }
        });*/
    }
}
