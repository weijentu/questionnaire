package com.tina.questionnaire;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/8
 * description:
 */

public interface OnPageChangeListener {

    void onNext(QuestionsObject object);
    void onPrev(QuestionsObject object);

}
