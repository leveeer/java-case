package com.jxau.xw.service.impl;

import com.jxau.xw.dao.impl.MessageDaoImpl;
import com.jxau.xw.domain.Message;
import com.jxau.xw.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    private MessageDaoImpl dao = new MessageDaoImpl();

    @Override
    public List<Message> findAllMessage() {
        return dao.findAll();
    }

    @Override
    public void addMessage(Message message) {
        dao.add(message);
    }

    @Override
    public void deleteMessage(String id) {
        dao.delete(Integer.parseInt(id));
    }
}
