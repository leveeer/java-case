package com.jxau.xw.service;

import com.jxau.xw.domain.QuestionnaireMain;

import java.util.List;

public interface QuestionnaireMainService {
    /**
     * 查询所有问卷
     * @return
     */
    List<QuestionnaireMain> findAllQuestionnaire();

    /**
     * 添加问卷
     * @param questionnaireMain
     */
    void addQuestionnaire(QuestionnaireMain questionnaireMain);

    /**
     * 删除问卷
     * @param id
     */
    void deleteQuestionnaire(String id);

    /**
     * 根据id查询问卷
     * @param id
     * @return
     */
    QuestionnaireMain findQuestionnaireById(String id);

    /**
     * 修改问卷内容
     * @param questionnaireMain
     */
    void updateQuestionnaire(QuestionnaireMain questionnaireMain);

    /**
     * 查找已经启用的问卷
     * @return
     */
    List<QuestionnaireMain> findQuestionnaireByIsuUse();
}
