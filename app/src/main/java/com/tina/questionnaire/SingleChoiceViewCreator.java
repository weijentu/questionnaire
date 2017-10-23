package com.tina.questionnaire;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private List<RadioButton> mList;

    public SingleChoiceViewCreator(Context context, QuestionsObject object) {
        super(context, object);
    }

    @Override
    public View getView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_questionnaire_new, null, false);
        mTvTtitle = (TextView) view.findViewById(R.id.tv_question_title);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_textinput);
        mLinearLayout.addView(childView);
        initData();
        return view;
    }


    public void initData() {
        mTvTtitle.setText(mObject.title);
        mList = new ArrayList<>();
        /**
         * Dynamically generate radio buttons according to the number of options
         */
        for (int i = 0; i < mObject.options.size(); i++) {
            String item = mObject.options.get(i);
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
                if (mObject.hasText && mList.get(mObject.textIndex).isChecked()){
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
        if(!mObject.hasText || selectedId != mObject.textIndex){
            mObject.answer = mObject.options.get(selectedId);
            return true;
        }
        return super.check();
    }
}
