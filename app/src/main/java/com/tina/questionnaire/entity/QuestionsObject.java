package com.tina.questionnaire.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/1
 * description:
 */

public class QuestionsObject implements Serializable{

    public static final String SINGLE_CHOICE = "single_choice";
    public static final String MULTI_CHOICE = "multiple_choice";
    public static final String DATE_PICKER = "date_picker";
    public static final String TEXT_INPUT = "text_input";
    /**
     * type : SingleChoice
     * index : 1
     * title : What is your gender?
     * hasText : false
     * options : ["Male","Famle","I prefer not to answer"]
     */

    public String type;      //question type : SingleChoice; MultipleChoice; DatePicker
    public int index;        //question index
    public int textIndex;    //if the option of the textIndex is selected, show editbox
    public String title;     //question
    public boolean hasText;  //if contains text input
    public List<String> options; //selections

    public String answer;   // answer from customer

}
