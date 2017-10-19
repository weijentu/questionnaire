package com.tina.questionnaire;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/8
 * description: Listen to the prev and next button in CommonFragment Activity,
                callback onPrev() and onNext() method in Questionnaire Activity which implements this interface
 */

public interface OnPageChangeListener {


    void onNext(int index, QuestionsObject object);

    void onPrev(int index, QuestionsObject object);

}
