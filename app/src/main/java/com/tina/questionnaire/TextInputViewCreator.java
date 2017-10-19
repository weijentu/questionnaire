package com.tina.questionnaire;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/8
 * description:
 */

public class TextInputViewCreator extends CommonTextInputViewCreator implements QuestionViewCreator {

    private QuestionsObject mObject;
    private TextView mTvTtitle;
    private ViewGroup mLinearLayout;


    @Override
    public View getView(Context context, QuestionsObject object) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_textinput, null, false);
        mObject = object;
        mTvTtitle = (TextView)view.findViewById(R.id.tv_question_title);
        mLinearLayout = (ViewGroup)view.findViewById(R.id.ll_parent);
        getTextInputView(context,mLinearLayout,object);
        initData();
        return view;
    }
    private void initData(){
        mTvTtitle.setText(mObject.title);
    }

}
