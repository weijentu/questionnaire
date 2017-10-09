package com.tina.questionnaire;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tina.questionnaire.entity.QuestionsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/8
 * description:
 */

public class MultiChoiceViewCreator implements QuestionViewCreator {

    private QuestionsObject mQuestionsObject;

    private TextView mTvTtitle;
    private Context mContext;
    private LinearLayout mLlCheckBox;

    private List<CheckBox> mCheckBoxList;
    private EditText mEditText;
    private LinearLayout mLlText;


    @Override
    public View getView(Context context, QuestionsObject object) {
        mContext = context;
        mQuestionsObject = object;
        View view = LayoutInflater.from(context).inflate(R.layout.multichoice_layout,null, false);
        mTvTtitle = (TextView)view.findViewById(R.id.tv_question_title);
        mLlCheckBox = (LinearLayout) view.findViewById(R.id.ll_checkboxes);
        mLlText = (LinearLayout)view.findViewById(R.id.ll_answer);
        mEditText = (EditText)view.findViewById(R.id.et_answer);
        initData();
        return view;
    }
    private void initData(){
        mTvTtitle.setText(mQuestionsObject.title);
        mCheckBoxList = new ArrayList<>();
        /**
         * dynamically generate checkbox according to the number of options
         */
        for (int i = 0; i < mQuestionsObject.options.size(); i++){
            String item = mQuestionsObject.options.get(i);
            CheckBox cb = new CheckBox(mContext);
            cb.setText(item);
            cb.setChecked(false);
            cb.setId(i);
            mLlCheckBox.addView(cb);
            mCheckBoxList.add(cb);
        }
        /**
         * if input text is needed, listen to the selected options to dynamically generate EditText
         */
        if(mQuestionsObject.hasText) {
            mCheckBoxList.get(mQuestionsObject.textIndex).setOnCheckedChangeListener(mOnCheckedChangeListener);
        }

    }
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(mQuestionsObject.hasText && isChecked){
                mLlText.setVisibility(View.VISIBLE);
            }else {
                mLlText.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public boolean check() {
        StringBuilder sb = new StringBuilder();
        for(CheckBox checkBox : mCheckBoxList){
            if(checkBox.isChecked()){
                sb.append(checkBox.getText().toString() + ";");
            }
        }
        if(TextUtils.isEmpty(sb.toString())){
            Toast.makeText(mContext,"Please select at least one option", Toast.LENGTH_SHORT).show();
            return false;
        }else if(mQuestionsObject.hasText && mCheckBoxList.get(mQuestionsObject.textIndex).isChecked() && TextUtils.isEmpty(mEditText.getText().toString())) {
            Toast.makeText(mContext, "Please enter your answer", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mQuestionsObject.hasText && mCheckBoxList.get(mQuestionsObject.textIndex).isChecked()){
            sb.append(mEditText.getText().toString());
        }
        /**
         * save input answer
         */
        mQuestionsObject.answer = sb.toString();
        return true;
    }
}
