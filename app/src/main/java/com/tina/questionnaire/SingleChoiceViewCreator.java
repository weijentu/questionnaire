package com.tina.questionnaire;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
 * on 2017/10/19
 * description:
 */

public class SingleChoiceViewCreator extends CommonTextInputViewCreator implements QuestionViewCreator {
    private TextView mTvTtitle;
    private LinearLayout mLinearLayout;
    private RadioGroup mRadioGroup;
    private QuestionsObject mQuestionsObject;
    private Context mContext;
    private List<RadioButton> mList;

    @Override
    public View getView(Context context, QuestionsObject object) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_questionnaire_new, null, false);
        mTvTtitle = (TextView) view.findViewById(R.id.tv_question_title);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_textinput);
        getTextInputView(context,mLinearLayout,object);
        mQuestionsObject = object;
        mContext = context;
        initData();

        return view;
    }


    public void initData() {
        mTvTtitle.setText(mQuestionsObject.title);
        mList = new ArrayList<>();
        /**
         * Dynamically generate radio buttons according to the number of options
         */
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
        if (selectedId == -1 ) {
            Toast.makeText(mContext, "Please select one option", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!mQuestionsObject.hasText || selectedId != mQuestionsObject.textIndex){
            mQuestionsObject.answer = mQuestionsObject.options.get(selectedId);
            return true;
        }
        return super.check();
    }
}
