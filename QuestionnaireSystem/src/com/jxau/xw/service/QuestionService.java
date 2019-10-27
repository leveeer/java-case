package com.jxau.xw.service;

import com.jxau.xw.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findQuestionByProjectid(String id);
}
