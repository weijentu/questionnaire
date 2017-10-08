package com.tina.questionnaire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tina.questionnaire.entity.QuestionsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/3
 * description:
 */

public class SingleChoiceFragment extends BaseFragment {

    QuestionsObject mQuestionsObject;

    private TextView mTvTtitle;

    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton[] mRadioButtons = {mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4};
    private RadioGroup mRadioGroup;
    private Button mBtn;

    private OnPageNextLinstener mLinstener;


    @Override
    protected View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questionnaire, null,false);
    }

    @Override
    protected void initViews() {
        mTvTtitle = (TextView)findViewById(R.id.tv_question_title);
//        mRadioButton1 = (RadioButton)findViewById(R.id.rb_reason1);
//        mRadioButton2 = (RadioButton)findViewById(R.id.rb_reason2);
//        mRadioButton3 = (RadioButton)findViewById(R.id.rb_reason3);
//        mRadioButton4 = (RadioButton)findViewById(R.id.rb_other_reason);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg);
        mBtn = (Button) findViewById(R.id.btn_submit);
        if(mQuestionsObject.index == questionsNum){
            mBtn.setText("Submit");
        }else{
            mBtn.setText("Cancle");
        }
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
    }





    @Override
    protected void initData(Bundle savedInstanceState) {
        if (mActivity instanceof QuestionnaireActivity){
            mLinstener= (OnPageNextLinstener) mActivity;
        }
        mTvTtitle.setText(mQuestionsObject.title);
        int size = mQuestionsObject.options.size(); //size needs to be less equal to mRadioButtons.length
        for(int i = 0; i < size; i++){
            mRadioButtons[i].setText(mQuestionsObject.options.get(i));
        }
        for(int i = size; i < mRadioButtons.length; i++){
            mRadioButtons[i].setVisibility(View.GONE);
        }
        refreshData();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                saveData();
            }
        });
    }


    @Override
    public void refreshData() {

        if(QuestionnaireActivity.map.get(mQuestionsObject.index)!= null){
            mRadioGroup.check(QuestionnaireActivity.map.get(mQuestionsObject.index).selectedId);
        }else {
            mRadioGroup.clearCheck();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        //---
        if (mLinstener!=null){
            mLinstener.onNext(mQuestionsObject);
        }

    }

    @Override
    public boolean saveData() {
        if(mRadioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(getActivity(),"Please select one option", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            int selectedId = mRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = (RadioButton)findViewById(selectedId);
            List<String> list = new ArrayList<>();
            list.add(selectedRadioButton.getText().toString());
            Results.getInstance().answers.add(mQuestionsObject.index,new ArrayList<>(list));
            if(QuestionnaireActivity.map.get(mQuestionsObject.index)!= null){
                QuestionnaireActivity.map.get(mQuestionsObject.index).selectedId = selectedId;
            }else{
                CashedAnswers cashedAnswers = new CashedAnswers();
                cashedAnswers.selectedId = selectedId;
                QuestionnaireActivity.map.put(mQuestionsObject.index,cashedAnswers);
            }
        }
        return true;
    }

}
