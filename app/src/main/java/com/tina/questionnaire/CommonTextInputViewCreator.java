package com.tina.questionnaire;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/19
 * description:
 */

public abstract class CommonTextInputViewCreator {

    private QuestionsObject mObject;
    private EditText mEditText;
    private Context mContext;


    public View getTextInputView(Context context, ViewGroup parent, QuestionsObject object) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_common_textinput,parent,true);
        mObject = object;
        mEditText = (EditText)view.findViewById(R.id.et_answer);
        return view;
    }

    public boolean check() {
        if(TextUtils.isEmpty(mEditText.getText().toString())){
            Toast.makeText(mContext,"Please enter your answer",Toast.LENGTH_SHORT).show();
            return false;
        }
        mObject.answer = mEditText.getText().toString();
        return true;
    }

}
