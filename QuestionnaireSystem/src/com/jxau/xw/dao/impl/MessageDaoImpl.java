package com.jxau.xw.dao.impl;

import com.jxau.xw.dao.MessageDao;
import com.jxau.xw.domain.Message;
import com.jxau.xw.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MessageDaoImpl implements MessageDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Message> findAll() {
        String sql = "select * from questionnaire_message";
        List<Message> messages = template.query(sql, new BeanPropertyRowMapper<Message>(Message.class));
        return messages;
    }

    @Override
    public void add(Message message) {
        String sql = "insert into questionnaire_message values(null,?,?,?)";
        template.update(sql,message.getCreatetime(),message.getMessage(),message.getEditor());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from questionnaire_message where id = ?";
        template.update(sql,id);
    }
}
