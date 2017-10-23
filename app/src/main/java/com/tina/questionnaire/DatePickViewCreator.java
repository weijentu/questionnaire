package com.tina.questionnaire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.tina.questionnaire.entity.QuestionsObject;

import java.util.Calendar;

/**
 * Created by Tina
 * on 2017/10/9
 * description:
 */

public class DatePickViewCreator implements QuestionViewCreator {

    private QuestionsObject mObject;
    private TextView mTextView;
    private DatePicker mDatePicker;
    private Context mContext;

    public DatePickViewCreator(Context context, QuestionsObject object){
        mContext = context;
        mObject = object;
    }


    @Override
    public View getView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_datepick,null,false);
        mTextView = (TextView)view.findViewById(R.id.tv_question_title);
        mDatePicker = (DatePicker)view.findViewById(R.id.datePicker);
        initData();
        return view;
    }

    private void initData(){
        mTextView.setText(mObject.title);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
            }
        });

    }

    @Override
    public boolean check() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        /**
         * make sure the birth date is not in the future
         */
        if(mDatePicker.getYear() > year || (mDatePicker.getYear() == year && mDatePicker.getMonth() > month)
                || (mDatePicker.getYear() == year && mDatePicker.getMonth() == month && mDatePicker.getDayOfMonth() > day)){
            Toast.makeText(mContext, "Selected date must be less than today",Toast.LENGTH_SHORT).show();
            return false;
        }
        StringBuffer time = new StringBuffer();
        time.append(mDatePicker.getYear());
        time.append("-");
        time.append(mDatePicker.getMonth());
        time.append("-");
        time.append(mDatePicker.getDayOfMonth());
        /**
         * save birth date to answer
         */
        mObject.answer = time.toString();
        return true;
    }
}
