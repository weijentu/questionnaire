package com.tina.questionnaire;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/19
 * description:
 */

public abstract class CommonTextInputViewCreator {

    protected QuestionsObject mObject;
    protected Context mContext;
    protected View childView;
    private EditText mEditText;

    public CommonTextInputViewCreator(Context context, QuestionsObject object){
        mContext = context;
        mObject = object;
        childView = getTextInputView();
    }
    public View getTextInputView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_common_textinput,null,true);
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
