package com.jxau.dao;

import com.jxau.domain.Log;

import java.util.List;

public interface LogDao {
    /**
     * 根据id查找用户记录
     * @param id
     * @return
     */
    List<Log> findRecordById(int id);


    /**
     * 插入记录
     * @param log
     */
    void insertLog(Log log);
}
