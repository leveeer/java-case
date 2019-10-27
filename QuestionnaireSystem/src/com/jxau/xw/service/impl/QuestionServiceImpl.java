package com.jxau.xw.service.impl;


import com.jxau.xw.dao.impl.QuestionDaoImpl;
import com.jxau.xw.domain.Question;
import com.jxau.xw.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDaoImpl dao = new QuestionDaoImpl();

    @Override
    public List<Question> findQuestionByProjectid(String id) {
        return dao.findByProjectid(Integer.parseInt(id));
    }
}
