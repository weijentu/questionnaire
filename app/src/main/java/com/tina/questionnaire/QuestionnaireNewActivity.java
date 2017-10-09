package com.tina.questionnaire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.tina.questionnaire.entity.QuestionsObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/8
 * description:
 */

public class QuestionnaireNewActivity extends AppCompatActivity implements OnPageChangeListener {

    private final static String QUESTIONNAIRE_KEY = "QuestionnaireResult";
    private List<QuestionsObject> mList;
    List<Fragment> mFragments = new ArrayList<>();
    public static final String QUESTION_OBJECT = "question_object";
    public static final String QUESTION_SIZE = "question_size";
    private QuestionViewPager mViewPager;
    private TextView mCancelButton;
    private List<QuestionsObject> resultList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        getList("questions.json");
        initViews();
    }

    public void initViews() {
        mCancelButton = (TextView) findViewById(R.id.btn_cancel);
        /**
         * generate fragment for each question
         */
        for (int i = 0; i < mList.size(); i++) {
            mFragments.add(new CommonFragment());
            Bundle bundle = new Bundle();
            bundle.putSerializable(QUESTION_OBJECT, mList.get(i));
            bundle.putInt(QUESTION_SIZE, mList.size());
            mFragments.get(i).setArguments(bundle);
        }
        /**
         * set cancel button on each question page
         */
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionnaireNewActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        QuesFragAdapter adapter = new QuesFragAdapter(getSupportFragmentManager(), mFragments);
        mViewPager = (QuestionViewPager) findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * read from local json file under assets folder
     * @param fileName
     */

    public void getList(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = this.getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(stringBuilder.toString())) {
            mList = JSON.parseArray(stringBuilder.toString(), QuestionsObject.class);
        }
        if (mList == null) {
            mList = new ArrayList<>();
        }
    }

    /**
     * save answer for each questions to a list when the next button is clicked, and then move to the next question,
     * and save the answer list to SharedPreference in Json form when the submit button in the last question is clicked
     *
     * @param object
     */

    @Override
    public void onNext(QuestionsObject object) {

        resultList.add(object.index, object);
        if (object.index == (mList.size() - 1)) {
            if (resultList != null) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(QUESTIONNAIRE_KEY, JSON.toJSONString(resultList));
                edit.apply();
            }
            startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else {
            mViewPager.setCurrentItem(object.index + 1);
        }

    }

    /**
     * move to the previous question
     * @param object
     */

    @Override
    public void onPrev(QuestionsObject object) {
        mViewPager.setCurrentItem(object.index - 1);
    }
}
