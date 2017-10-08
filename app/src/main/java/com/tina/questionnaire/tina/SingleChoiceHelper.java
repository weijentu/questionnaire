package com.tina.questionnaire.tina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tina.questionnaire.CashedAnswers;
import com.tina.questionnaire.QuestionnaireActivity;
import com.tina.questionnaire.R;
import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by carrey
 * on 2017/10/8
 * description:
 */

public class SingleChoiceHelper implements QuestionHelper {

    private TextView mTvTtitle;

    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton[] mRadioButtons = {mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4};
    private RadioGroup mRadioGroup;
    private QuestionsObject mQuestionsObject;
    private int size;
    private Context mContext;
    private View view;


    @Override
    public View getView(Context context, QuestionsObject object, int size) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fragment_questionnaire, null, false);
        mTvTtitle = (TextView) view.findViewById(R.id.tv_question_title);
        mRadioButton1 = (RadioButton) view.findViewById(R.id.rb1);
        mRadioButton2 = (RadioButton) view.findViewById(R.id.rb2);
        mRadioButton3 = (RadioButton) view.findViewById(R.id.rb3);
        mRadioButton4 = (RadioButton) view.findViewById(R.id.rb4);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg);

        mQuestionsObject = object;
        mContext = context;
        this.size = size;
        bindView();

        return view;
    }


    public void bindView() {
        mTvTtitle.setText(mQuestionsObject.title);

        for (String item : mQuestionsObject.options) {

//            TimePicker
            RadioButton radiobutton=new RadioButton(mContext);
//            radiobutton.setId(setId);
            radiobutton.setText(item);
            radiobutton.setLayoutParams(new RadioGroup.LayoutParams(380,200));
            mRadioGroup.addView(radiobutton);
        }

        mRadioGroup.clearCheck();

    }

    @Override
    public boolean check() {
        if (mRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(mContext, "Please select one option", Toast.LENGTH_SHORT).show();
return false;
        } else {
            int selectedId = mRadioGroup.getCheckedRadioButtonId();

            switch (selectedId){
                case 1 :
//                    mRadioGroup.findViewById();
                    break;

            }
            RadioButton selectedRadioButton = (RadioButton) view.findViewById(selectedId);
            mQuestionsObject.answer = selectedRadioButton.getText().toString();
            if (QuestionnaireActivity.map.get(mQuestionsObject.index) != null) {
                QuestionnaireActivity.map.get(mQuestionsObject.index).selectedId = selectedId;
            } else {
                CashedAnswers cashedAnswers = new CashedAnswers();
                cashedAnswers.selectedId = selectedId;
                QuestionnaireActivity.map.put(mQuestionsObject.index, cashedAnswers);
            }
        }
        return true;
    }
}
