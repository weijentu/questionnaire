package com.tina.questionnaire;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class MultiChoiceViewCreator extends CommonTextInputViewCreator implements QuestionViewCreator {

    private TextView mTvTtitle;
    private LinearLayout mLlCheckBox;
    private List<CheckBox> mCheckBoxList;
    private LinearLayout mLlText;

    public MultiChoiceViewCreator(Context context, QuestionsObject object) {
        super(context, object);
    }


    @Override
    public View getView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.multichoice_layout,null, false);
        mTvTtitle = (TextView)view.findViewById(R.id.tv_question_title);
        mLlCheckBox = (LinearLayout) view.findViewById(R.id.ll_checkboxes);
        mLlText = (LinearLayout)view.findViewById(R.id.ll_textinput);
        mLlText.addView(childView);
        initData();
        return view;
    }
    private void initData(){
        mTvTtitle.setText(mObject.title);
        mCheckBoxList = new ArrayList<>();
        /**
         * dynamically generate checkbox according to the number of options
         */
        for (int i = 0; i < mObject.options.size(); i++){
            String item = mObject.options.get(i);
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
        if(mObject.hasText) {
            mCheckBoxList.get(mObject.textIndex).setOnCheckedChangeListener(mOnCheckedChangeListener);
        }

    }
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(mObject.hasText && isChecked){
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
        }
        if(mObject.hasText && mCheckBoxList.get(mObject.textIndex).isChecked()){
            boolean value = super.check();
            if(!value){
                return false;
            }
            sb.append(mObject.answer);
        }

        /**
         * save input answer
         */
        mObject.answer = sb.toString();
        return true;
    }
}
