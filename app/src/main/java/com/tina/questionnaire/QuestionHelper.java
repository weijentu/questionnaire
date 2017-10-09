package com.tina.questionnaire;

import android.content.Context;
import android.view.View;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/8
 * description:
 */

public interface QuestionHelper {
    View getView(Context context, QuestionsObject object);
    boolean check();
}
