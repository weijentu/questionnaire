package com.tina.questionnaire;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/2
 * description:
 */

public class QuestionnaireViewHolder {
    public static class SingleChoiceViewHolder{
        QuestionsObject mQuestionsObject;

        private TextView mTvTtitle;

        private RadioButton mRadioButton1;
        private RadioButton mRadioButton2;
        private RadioButton mRadioButton3;
        private RadioButton mRadioButton4;
        private RadioGroup mRadioGroup;
        private RadioButton[] mRadioButtons = {mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4};

        private LinearLayout mLlQuestions;
        private LinearLayout mLlSingleChoice;
        private LinearLayout mLlMultiChoice;
        private LinearLayout mLlDatePicker;
        private int size = mQuestionsObject.options.size(); //size needs to be less equal to mRadioButtons.length

        public SingleChoiceViewHolder(View view, QuestionsObject questions){
            mTvTtitle = (TextView)view.findViewById(R.id.tv_question_title);
            mRadioButton1 = (RadioButton)view.findViewById(R.id.rb1);
            mRadioButton2 = (RadioButton)view.findViewById(R.id.rb2);
            mRadioButton3 = (RadioButton)view.findViewById(R.id.rb3);
            mRadioButton4 = (RadioButton)view.findViewById(R.id.rb4);
            mRadioGroup = (RadioGroup) view.findViewById(R.id.rg);
            mLlQuestions = (LinearLayout)view.findViewById(R.id.ll_question_all);
            mLlSingleChoice = (LinearLayout)view.findViewById(R.id.ll_single_choice);
            mLlMultiChoice = (LinearLayout)view.findViewById(R.id.ll_multichoice_parent);
            mLlDatePicker = (LinearLayout)view.findViewById(R.id.ll_datepicker_parent);
            mQuestionsObject = questions;

        }
        public void bindView(){
            mTvTtitle.setText(mQuestionsObject.title);

            for(int i = 0; i < size; i++){
                mRadioButtons[i].setText(mQuestionsObject.options.get(i));
            }
            for(int i = size; i < mRadioButtons.length; i++){
                mRadioButtons[i].setVisibility(View.GONE);
            }
            mLlMultiChoice.setVisibility(View.GONE);
            mLlDatePicker.setVisibility(View.GONE);
            mRadioGroup.clearCheck();
        }
        public View getSingleChoice(){
            return mLlQuestions;
        }

    }

    public static class MultiChoiceViewHolder {
        QuestionsObject mQuestionsObject;

        private TextView mTvTtitle;

        private CheckBox mCheckBox1;
        private CheckBox mCheckBox2;
        private CheckBox mCheckBox3;
        private CheckBox mCheckBox4;

        private CheckBox[] mCheckBoxes = {mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4};

        private LinearLayout mLlQuestions;
        private LinearLayout mLlSingleChoice;
        private LinearLayout mLlMultiChoice;
        private LinearLayout mLlDatePicker;

        public MultiChoiceViewHolder(View view, QuestionsObject questions){
            mTvTtitle = (TextView)view.findViewById(R.id.tv_question_title);
            mCheckBox1 = (CheckBox) view.findViewById(R.id.checkBox1);
            mCheckBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
            mCheckBox3 = (CheckBox) view.findViewById(R.id.checkBox3);
            mCheckBox4 = (CheckBox) view.findViewById(R.id.checkBox4);

            mLlQuestions = (LinearLayout)view.findViewById(R.id.ll_question_all);
            mLlSingleChoice = (LinearLayout)view.findViewById(R.id.ll_single_choice);
            mLlMultiChoice = (LinearLayout)view.findViewById(R.id.ll_multichoice_parent);
            mLlDatePicker = (LinearLayout)view.findViewById(R.id.ll_datepicker_parent);
            mQuestionsObject = questions;
        }
        public void bindView(){
            mTvTtitle.setText(mQuestionsObject.title);
            int size = mQuestionsObject.options.size(); //size needs to be less equal to mRadioButtons.length
            for(int i = 0; i < size; i++){
                mCheckBoxes[i].setText(mQuestionsObject.options.get(i));
            }
            for(int i = size; i < mCheckBoxes.length; i++){
                mCheckBoxes[i].setVisibility(View.GONE);
            }
            mLlSingleChoice.setVisibility(View.GONE);
            mLlDatePicker.setVisibility(View.GONE);
        }
        public View getMultiChoice(){
            return mLlQuestions;
        }

    }
}
