package com.tina.questionnaire;

import android.content.Context;
import android.view.View;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/8
 * description: Interface used to allow the CommonFragment to get view from different type of QuestionViewCreator
 */

public interface QuestionViewCreator {
    View getView(Context context, QuestionsObject object);
    boolean check();
}
