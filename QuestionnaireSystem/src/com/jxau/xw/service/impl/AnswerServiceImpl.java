package com.jxau.xw.service.impl;

import com.jxau.xw.dao.impl.AnswerDaoImpl;
import com.jxau.xw.domain.Answer;
import com.jxau.xw.service.AnswerService;

public class AnswerServiceImpl implements AnswerService {

    private AnswerDaoImpl dao = new AnswerDaoImpl();

    @Override
    public void addAnswer(Answer answer) {
        dao.add(answer);
    }
}
