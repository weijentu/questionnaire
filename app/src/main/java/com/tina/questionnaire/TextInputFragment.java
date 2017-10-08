package com.tina.questionnaire;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/5
 * description:
 */

public class TextInputFragment extends BaseFragment {

    private TextView mTvTtitle;
    private Button mBtn;
    private EditText mEditText;
    @Override
    protected View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_textinput,null, false);
    }

    @Override
    protected void initViews() {
        mTvTtitle = (TextView)findViewById(R.id.tv_question_title);
        mEditText = (EditText)findViewById(R.id.et_answer);
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

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                saveData();
            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTvTtitle.setText(mQuestionsObject.title);
        refreshData();
    }

    @Override
    public void refreshData() {
        if(QuestionnaireActivity.map.get(mQuestionsObject.index) != null){
            mEditText.setText(QuestionnaireActivity.map.get(mQuestionsObject.index).text);
        }
    }

    @Override
    public boolean saveData() {
        if(mEditText.getText().toString().equals(" ")){
            Toast.makeText(getActivity(),"Please enter your answer!" ,Toast.LENGTH_SHORT).show();
            return false;
        }
        List<String> list = new ArrayList<>();
        list.add(mEditText.getText().toString());
        Results.getInstance().answers.add(mQuestionsObject.index, new ArrayList<>(list));
        if(QuestionnaireActivity.map.get(mQuestionsObject.index)!= null){
            QuestionnaireActivity.map.get(mQuestionsObject.index).text = mEditText.getText().toString();
        }else{
            CashedAnswers cashedAnswers = new CashedAnswers();
            cashedAnswers.text = mEditText.getText().toString();
            QuestionnaireActivity.map.put(mQuestionsObject.index,cashedAnswers);
        }
        return true;
    }
}
