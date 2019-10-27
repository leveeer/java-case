package com.jxau.bank.dao;

import com.jxau.bank.model.OperateBean;

import java.awt.print.Book;
import java.util.Vector;

public interface OperateDaoInterface {


    /**
     * 插入一条操作记录
     * @param operateBean
     */
    void insertRecord(OperateBean operateBean);


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Vector<OperateBean> findRecordByUsername(String username);
}
