package com.tina.questionnaire.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/1
 * description:
 */

public class QuestionsObject implements Serializable{

    public static final String SINGLE_CHOICE = "singleChoice";
    public static final String MULTI_CHOICE = "multipleChoice";
    public static final String DATE_PICKER = "datePicker";
    public static final String TEXT_INPUT = "text_input";
    /**
     * type : SingleChoice
     * index : 1
     * title : What is your gender?
     * hasText : false
     * options : ["Male","Famle","I prefer not to answer"]
     */

    public String type;      //那种类型的问题 :SingleChoice; MultipleChoice; DatePicker
    public int index;        //第几个问题
    public String title;     //题目
    public boolean hasText;  //是否有文字栏位
    public List<String> options; //问题的选项

    public String answer;//

    @Override
    public String toString() {
        return type + index + title + hasText + options.get(0);
    }
}
