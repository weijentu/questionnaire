package com.tina.questionnaire;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.tina.questionnaire.entity.QuestionsObject;

/**
 * Created by Tina
 * on 2017/10/1
 * description:
 */

public abstract class BaseFragment extends Fragment {
    View mRootView;
    QuestionsObject mQuestionsObject;
    int questionsNum;
    private final static String QUESTIONNAIRE_KEY = "QuestionnaireResult";

    protected Context mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void startActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        super.startActivity(intent);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = this.onCreateView(inflater, savedInstanceState);
            this.initViews();
            this.initData(savedInstanceState);
        }
        mQuestionsObject = (QuestionsObject)getArguments().getSerializable(QuestionnaireActivity.QUESTION_OBJECT);
        questionsNum = getArguments().getInt(QuestionnaireActivity.QUESTION_SIZE);
        return mRootView;
    }

    protected void onButtonClick(){
        if(mQuestionsObject.index == questionsNum){
            saveDataToSharedPref();
        } else {
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
    }

    private void saveDataToSharedPref(){
        if(Results.getInstance() != null){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(QUESTIONNAIRE_KEY, JSON.toJSONString(Results.getInstance()));
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }


    protected abstract View onCreateView(LayoutInflater inflater, Bundle savedInstanceState);

    protected abstract void initViews();

    protected abstract void initData(Bundle savedInstanceState);

    protected View findViewById(int id) {
        return mRootView.findViewById(id);
    }

    public View getRootView() {
        return mRootView;
    }

    public abstract void refreshData();

    public abstract boolean saveData();

}
