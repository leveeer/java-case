package com.jxau.xw.dao.impl;

import com.jxau.xw.dao.QuestionnaireMainDao;
import com.jxau.xw.domain.QuestionnaireMain;
import com.jxau.xw.utils.DateUtils;
import com.jxau.xw.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class QuestionnaireMainDaoImpl implements QuestionnaireMainDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<QuestionnaireMain> findAll() {
        String sql = "select * from questionnaire_main";
        List<QuestionnaireMain> questionnaires = template.query(sql, new BeanPropertyRowMapper<QuestionnaireMain>(QuestionnaireMain.class));
        return questionnaires;
    }

    @Override
    public void addQuestinnaire(QuestionnaireMain questionnaireMain) {

        System.out.println(questionnaireMain.getCreatetime());
        String sql = "insert into questionnaire_main values(null,?,?,?,?,?,?)";
        template.update(sql,questionnaireMain.getTitle(),questionnaireMain.getIntroduce(),questionnaireMain.getCreatetime(),questionnaireMain.getIsuse(),questionnaireMain.getEndtime(),questionnaireMain.getEditor());
    }

    @Override
    public void deleteQuestionnaire(int id) {
        String sql = "delete from questionnaire_main where id = ?";
        template.update(sql,id);
    }

    @Override
    public QuestionnaireMain findQuestionnaireById(int id) {
        String sql = "select * from questionnaire_main where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<QuestionnaireMain>(QuestionnaireMain.class),id);
    }

    @Override
    public void updateQuestionnaire(QuestionnaireMain questionnaireMain) {
       String sql = "update questionnaire_main set title = ?,createtime = ?,isuse = ?,endtime = ?,editor = ?,introduce = ? where id = ?" ;
       template.update(sql,questionnaireMain.getTitle(),questionnaireMain.getCreatetime(),questionnaireMain.getIsuse(),questionnaireMain.getEndtime(),questionnaireMain.getEditor(),questionnaireMain.getIntroduce(),questionnaireMain.getId());
    }

    @Override
    public List<QuestionnaireMain> findQuestionnaireByIsuse() {
        String sql = "select * from questionnaire_main where isuse = '是'";
        List<QuestionnaireMain> isUseQuestionnaire = template.query(sql, new BeanPropertyRowMapper<QuestionnaireMain>(QuestionnaireMain.class));
       /* for (QuestionnaireMain questionnaireMain : isUseQuestionnaire) {
            System.out.println(questionnaireMain);
        }*/
        return isUseQuestionnaire;
    }
}
