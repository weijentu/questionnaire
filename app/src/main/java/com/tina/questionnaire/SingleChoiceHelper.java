package com.tina.questionnaire;

import android.content.Context;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class SingleChoiceHelper implements QuestionHelper {

    private TextView mTvTtitle;
    private EditText mEditText;
    private LinearLayout mLinearLayout;
    private RadioGroup mRadioGroup;
    private QuestionsObject mQuestionsObject;
    private Context mContext;
    private List<RadioButton> mList;

    @Override
    public View getView(Context context, QuestionsObject object) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_questionnaire, null, false);
        mTvTtitle = (TextView) view.findViewById(R.id.tv_question_title);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_answer);
        mEditText = (EditText)view.findViewById(R.id.et_answer);
        mQuestionsObject = object;
        mContext = context;
        initData();

        return view;
    }


    public void initData() {
        mTvTtitle.setText(mQuestionsObject.title);
        mList = new ArrayList<>();
        for (int i = 0; i < mQuestionsObject.options.size(); i++) {
            String item = mQuestionsObject.options.get(i);
            RadioButton radioButton = new RadioButton(mContext);
            radioButton.setId(i);
            radioButton.setText(item);
            radioButton.setTextSize(14);
            radioButton.setGravity(Gravity.CENTER);
            mRadioGroup.addView(radioButton);
            mList.add(radioButton);
        }

        mRadioGroup.clearCheck();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mQuestionsObject.hasText && mList.get(mQuestionsObject.textIndex).isChecked()){
                    mLinearLayout.setVisibility(View.VISIBLE);
                }else {
                    mLinearLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean check() {
        int selectedId = mRadioGroup.getCheckedRadioButtonId();
        String text = mEditText.getText().toString();
        if (selectedId == -1 ) {
            Toast.makeText(mContext, "Please select one option", Toast.LENGTH_SHORT).show();
            return false;
        } else if(mQuestionsObject.hasText && selectedId == mQuestionsObject.textIndex && TextUtils.isEmpty(text)){
            Toast.makeText(mContext, "Please enter your answer", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mQuestionsObject.hasText && selectedId == mQuestionsObject.textIndex){
            mQuestionsObject.answer = mEditText.getText().toString();
        }else {
            mQuestionsObject.answer = mQuestionsObject.options.get(selectedId);
        }
        return true;
    }
}
