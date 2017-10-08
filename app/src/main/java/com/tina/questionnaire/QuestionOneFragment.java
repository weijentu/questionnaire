package com.tina.questionnaire;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/2
 * description:
 */

public class QuestionOneFragment extends BaseFragment{

    private QuestionnaireViewHolder.SingleChoiceViewHolder mSingleChoiceViewHolder;
    private QuestionnaireViewHolder.MultiChoiceViewHolder mMultiChoiceViewHolder;
    private String type;
    private RadioGroup mRadioGroup;
    @Override
    protected View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questionnaire, null);
    }

    @Override
    protected void initViews() {
        View v = findViewById(R.id.ll_question_parent);
        switch (type){
            case QuestionsObject.SINGLE_CHOICE:
                mSingleChoiceViewHolder = new QuestionnaireViewHolder.SingleChoiceViewHolder(v, mQuestionsObject);
                mRadioGroup = (RadioGroup)findViewById(R.id.rg);
                mRadioGroup.clearCheck();
                break;
            case QuestionsObject.MULTI_CHOICE:
                mMultiChoiceViewHolder = new QuestionnaireViewHolder.MultiChoiceViewHolder(v, mQuestionsObject);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void refreshData() {

    }

    @Override
    public void saveData() {

    }
}
