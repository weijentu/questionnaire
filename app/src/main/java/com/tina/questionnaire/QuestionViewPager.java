package com.tina.questionnaire;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Tina
 * on 2017/10/9
 * description:
 */

public class QuestionViewPager extends ViewPager {

    public QuestionViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuestionViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
