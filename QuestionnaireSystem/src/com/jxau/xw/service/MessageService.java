package com.jxau.xw.service;

import com.jxau.xw.domain.Message;

import java.util.List;

public interface MessageService {
    /**
     * 查询所有留言
     * @return
     */
    List<Message> findAllMessage();

    void addMessage(Message message);

    void deleteMessage(String id);
}
