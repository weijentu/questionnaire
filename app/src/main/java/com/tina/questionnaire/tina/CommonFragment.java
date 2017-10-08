package com.tina.questionnaire.tina;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tina.questionnaire.MultiChoiceFragment;
import com.tina.questionnaire.QuestionnaireActivity;
import com.tina.questionnaire.R;
import com.tina.questionnaire.SingleChoiceFragment;
import com.tina.questionnaire.TextInputFragment;
import com.tina.questionnaire.entity.QuestionsObject;


/**
 * Created by carrey
 * on 2017/10/8
 * description:
 */

public class CommonFragment extends Fragment{

    View mRootView;
    protected Context mActivity;
    private QuestionsObject mObject;
    private int questionsNum;
    private QuestionHelper mQuestionHelper;
    private ViewGroup mContent;
    private Button mButton;
    private OnPageNextListener mOnPageNextListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_common, null,false);

        mContent = (ViewGroup) findViewById(R.id.ll_content);

        mContent.addView(mQuestionHelper.getView(mActivity,mObject,questionsNum));
        mButton = (Button)findViewById(R.id.btn_submit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionHelper.check()){
                    mOnPageNextListener.onNext(mObject);
                }
            }
        });
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mObject = (QuestionsObject)getArguments().getSerializable(QuestionnaireActivity.QUESTION_OBJECT);
        questionsNum = getArguments().getInt(QuestionnaireActivity.QUESTION_SIZE);
        if(mObject.type.equals(QuestionsObject.SINGLE_CHOICE)){
            mQuestionHelper = new SingleChoiceHelper();
        }else if(mObject.type.equals(QuestionsObject.MULTI_CHOICE)){

        }else if(mObject.type.equals(QuestionsObject.TEXT_INPUT)){

        }
    }


    @Override
    public void startActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        super.startActivity(intent);
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (mRootView != null) {
//            ViewGroup parent = (ViewGroup) mRootView.getParent();
//            if (parent != null) {
//                parent.removeView(mRootView);
//            }
//        } else {
//            mRootView = this.onCreateView(inflater, savedInstanceState);
////            this.initData(savedInstanceState);
//        }
//        return mRootView;
//    }

    protected View findViewById(int id) {
        return mRootView.findViewById(id);
    }

}
