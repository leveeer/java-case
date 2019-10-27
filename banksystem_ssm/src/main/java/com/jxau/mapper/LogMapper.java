package com.jxau.mapper;

import com.jxau.domain.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LogMapper {
    /**
     * 根据id查找用户记录
     *
     * @param id
     * @return
     */
    List<Log> findRecordById(int id);


    /**
     * 插入记录
     *
     * @param log
     */
    void insertLog(Log log);

    List<Log> findAllLog(int id);

}
