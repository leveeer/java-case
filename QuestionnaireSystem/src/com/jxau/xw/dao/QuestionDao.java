package com.jxau.xw.dao;

import com.jxau.xw.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findByProjectid(int id);
}
