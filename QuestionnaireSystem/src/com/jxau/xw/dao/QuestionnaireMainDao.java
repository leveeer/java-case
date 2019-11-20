package com.jxau.xw.dao;

import com.jxau.xw.domain.QuestionnaireMain;

import java.util.List;

public interface QuestionnaireMainDao {
    List<QuestionnaireMain> findAll();

    void addQuestinnaire(QuestionnaireMain questionnaireMain);

    void deleteQuestionnaire(int id);

    QuestionnaireMain findQuestionnaireById(int id);

    void updateQuestionnaire(QuestionnaireMain questionnaireMain);

    List<QuestionnaireMain> findQuestionnaireByIsuse();
}
