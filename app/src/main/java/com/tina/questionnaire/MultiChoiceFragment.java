package com.tina.questionnaire;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tina.questionnaire.entity.QuestionsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/3
 * description:
 */

public class MultiChoiceFragment extends BaseFragment{

    QuestionsObject mQuestionsObject;

    private TextView mTvTtitle;

    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private CheckBox mCheckBox4;
    private Button mBtn;

    private CheckBox[] mCheckBoxes = {mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4};

    @Override
    protected View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.multichoice_layout, null,false);
    }

    @Override
    protected void initViews() {
        mTvTtitle = (TextView) findViewById(R.id.tv_question_title);
        mCheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        mCheckBox3 = (CheckBox) findViewById(R.id.checkBox3);
        mCheckBox4 = (CheckBox) findViewById(R.id.checkBox4);
        mCheckBox1.setOnCheckedChangeListener(mOnCheckedChangeListener);
        mCheckBox2.setOnCheckedChangeListener(mOnCheckedChangeListener);
        mCheckBox3.setOnCheckedChangeListener(mOnCheckedChangeListener);
        mCheckBox4.setOnCheckedChangeListener(mOnCheckedChangeListener);
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
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            saveData();
        }
    };


    @Override
    protected void initData(Bundle savedInstanceState) {
        mTvTtitle.setText(mQuestionsObject.title);
        /**
         * mQuestionsObject.options.size() needs to be less equal to mCheckBoxes.length
         */
        int size = mQuestionsObject.options.size();
        for(int i = 0; i < size; i++){
            mCheckBoxes[i].setText(mQuestionsObject.options.get(i));
        }
        for(int i = size; i < mCheckBoxes.length; i++){
            mCheckBoxes[i].setVisibility(View.GONE);
            mCheckBoxes[i].setChecked(false);
        }
        refreshData();

    }

    @Override
    public void refreshData() {
        if(QuestionnaireActivity.map.get(mQuestionsObject.index) != null){
            CashedAnswers answers = QuestionnaireActivity.map.get(mQuestionsObject.index);
            boolean[] isChecked = answers.isChecked;
            for(int i = 0; i < isChecked.length; i++){
                if(isChecked[i]){
                    mCheckBoxes[i].setChecked(true);
                }else {
                    mCheckBoxes[i].setChecked(false);
                }
            }
        }else{
            for(int i = 0; i < mCheckBoxes.length; i++){
                    mCheckBoxes[i].setChecked(false);
            }
        }
    }

    @Override
    public boolean saveData() {
        List<String> list = new ArrayList<>();
        boolean[] isChecked = new boolean[mCheckBoxes.length];
        for(int i = 0; i < mCheckBoxes.length; i++){
            if(mCheckBoxes[i].isChecked()){
                list.add(i, mCheckBoxes[i].getText().toString());
                isChecked[i] = true;
            }else {
                isChecked[i] = false;
            }
        }
        if(!mCheckBox1.isChecked() && !mCheckBox2.isChecked() && !mCheckBox3.isChecked() && !mCheckBox4.isChecked()){
            Toast.makeText(getActivity(),"Please select at least one option!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Results.getInstance().answers.add(mQuestionsObject.index,new ArrayList<>(list));
            if(QuestionnaireActivity.map.get(mQuestionsObject.index)!= null){
                QuestionnaireActivity.map.get(mQuestionsObject.index).isChecked = isChecked;
            }else{
                CashedAnswers cashedAnswers = new CashedAnswers();
                cashedAnswers.isChecked = isChecked;
                QuestionnaireActivity.map.put(mQuestionsObject.index,cashedAnswers);
            }
        }
        return true;
    }
}
