package com.tina.questionnaire;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/8
 * description:
 */

public class TextInputViewCreator implements QuestionViewCreator {

    private QuestionsObject mObject;
    private TextView mTvTtitle;
    private EditText mEditText;
    private Context mContext;

    @Override
    public View getView(Context context, QuestionsObject object) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_textinput, null, false);
        mObject = object;
        mTvTtitle = (TextView)view.findViewById(R.id.tv_question_title);
        mEditText = (EditText)view.findViewById(R.id.et_answer);
        initData();
        return view;
    }
    private void initData(){
        mTvTtitle.setText(mObject.title);
    }


    @Override
    public boolean check() {
        if(TextUtils.isEmpty(mEditText.getText().toString())){
            Toast.makeText(mContext,"Please enter your answer",Toast.LENGTH_SHORT).show();
            return false;
        }
        mObject.answer = mEditText.getText().toString();
        return true;
    }
}
