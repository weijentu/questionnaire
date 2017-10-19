package com.tina.questionnaire.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/1
 * description:
 */

public class QuestionsObject implements Serializable{

    public enum Type{
        SINGLE_CHOICE,MULTI_CHOICE,DATE_PICKER,TEXT_INPUT
    }

    public Type type;
    public int textIndex;    //if the option of the textIndex is selected, show editbox
    public String title;     //question
    public boolean hasText;  //if contains text input
    public List<String> options; //selections

    public String answer;   // answer from customer

}
