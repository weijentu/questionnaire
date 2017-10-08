package com.tina.questionnaire;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.tina.questionnaire.entity.QuestionsObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/5
 * description:
 */

public class QuestionnaireActivity extends AppCompatActivity  implements OnPageNextLinstener{

    private List<QuestionsObject> mList;
    List<Fragment> mFragments = new ArrayList<>();
    public static final String QUESTION_OBJECT= "question_object";
    public static final String QUESTION_SIZE = "question_size";
    public static final String MAP_KEY = "map_key";
    private ViewPager mViewPager;
    private int preItem = 0;
    public static HashMap<Integer,CashedAnswers> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
//        for(QuestionsObject questionsObject : mList){
//            String s = questionsObject.toString();
//            Log.e("json", s);
//        }
        getList("questions.json");
        if(savedInstanceState != null){
            map = (HashMap<Integer,CashedAnswers>) savedInstanceState.getSerializable(MAP_KEY);
        }else {
            map = new HashMap<Integer, CashedAnswers>(mList.size());
        }

        initViews();
    }

    public void initViews(){
        for(int i = 0; i < mList.size(); i++){
            if(mList.get(i).type.equals(QuestionsObject.SINGLE_CHOICE)){
                mFragments.add(new SingleChoiceFragment());
            }else if(mList.get(i).type.equals(QuestionsObject.MULTI_CHOICE)){
                mFragments.add(new MultiChoiceFragment());
            }else if(mList.get(i).type.equals(QuestionsObject.TEXT_INPUT)){
                mFragments.add(new TextInputFragment());
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(QUESTION_OBJECT,mList.get(i));
            bundle.putInt(QUESTION_SIZE,mList.size());
            mFragments.get(i).setArguments(bundle);
        }
        QuesFragAdapter adapter = new QuesFragAdapter(getSupportFragmentManager(),mFragments);
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                Fragment prevfragment = mFragments.get(preItem);
//                if(prevfragment != null) {
//                    if (prevfragment instanceof BaseFragment) {
//                        if(!((BaseFragment) prevfragment).saveData()){
//                            mViewPager.setCurrentItem(preItem);
//                            preItem = position;
//                            return;
//                        }
//                    }
//                }
//                preItem = position;
                Fragment curFragment = mFragments.get(position);
                if(curFragment != null){
                    if(curFragment instanceof BaseFragment){
                        ((BaseFragment)curFragment).refreshData();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void getList(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = this.getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!TextUtils.isEmpty(stringBuilder.toString())){
            mList = JSON.parseArray(stringBuilder.toString(),QuestionsObject.class);
        }
        if(mList == null) {
            mList = new ArrayList<>();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MAP_KEY,map);
    }

    @Override
    public void onNext(QuestionsObject object) {

      String json=  JSON.toJSONString(object);

        //// TODO: 2017/10/8

    }
}
