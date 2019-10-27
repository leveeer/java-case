package com.jxau.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jxau.dao.LogDao;
import com.jxau.domain.Log;
import com.jxau.util.JDBCUtils;

public class LogDaoImpl implements LogDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Log> findRecordById(int uid) {
        String    sql     = "select * from log where uid = ?";
        List<Log> logList = template.query(sql, new BeanPropertyRowMapper<Log>(Log.class), uid);

        return logList;
    }

    @Override
    public void insertLog(Log log) {
        String sql = "insert into log values(null,?,?,?,?,?,?,?)";

        template.update(sql,
                        log.getUid(),
                        log.getUsername(),
                        log.getOpType(),
                        log.getOpTime(),
                        log.getOpMoney(),
                        log.getBeforeMoney(),
                        log.getCurrentMoney());
    }
}


