package com.tina.questionnaire.tina;

import android.content.Context;
import android.view.View;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by carrey
 * on 2017/10/8
 * description:
 */

public interface QuestionHelper {
    View getView(Context context, QuestionsObject object, int size);
    boolean check();
}
