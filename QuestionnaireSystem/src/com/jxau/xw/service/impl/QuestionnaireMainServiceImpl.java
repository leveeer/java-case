package com.jxau.xw.service.impl;

import com.jxau.xw.dao.QuestionnaireMainDao;
import com.jxau.xw.dao.impl.QuestionnaireMainDaoImpl;
import com.jxau.xw.domain.QuestionnaireMain;
import com.jxau.xw.service.QuestionnaireMainService;


import java.util.List;

public class QuestionnaireMainServiceImpl implements QuestionnaireMainService {

    private QuestionnaireMainDao dao = new QuestionnaireMainDaoImpl();

    @Override
    public List<QuestionnaireMain> findAllQuestionnaire() {
        return dao.findAll();
    }

    @Override
    public void addQuestionnaire(QuestionnaireMain questionnaireMain) {
        dao.addQuestinnaire(questionnaireMain);
    }

    @Override
    public void deleteQuestionnaire(String id) {
        dao.deleteQuestionnaire(Integer.parseInt(id));
    }

    @Override
    public QuestionnaireMain findQuestionnaireById(String id) {
        return dao.findQuestionnaireById(Integer.parseInt(id));
    }

    @Override
    public void updateQuestionnaire(QuestionnaireMain questionnaireMain) {
        dao.updateQuestionnaire(questionnaireMain);
    }

    @Override
    public List<QuestionnaireMain> findQuestionnaireByIsuUse() {
        return dao.findQuestionnaireByIsuse();
    }
}
