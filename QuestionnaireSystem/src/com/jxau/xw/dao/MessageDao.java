package com.jxau.xw.dao;

import com.jxau.xw.domain.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findAll();

    void add(Message message);

    void delete(int id);
}
