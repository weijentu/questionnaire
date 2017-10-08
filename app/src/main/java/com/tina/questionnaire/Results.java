package com.tina.questionnaire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tina
 * on 2017/10/3
 * description:
 */

public class Results implements Serializable{

    private static Results sInstance;

    public static Results getInstance() {
        if (sInstance == null) {
            synchronized (Results.class) {
                if (sInstance == null) {
                    sInstance = new Results();
                }
            }
        }
        return sInstance;
    }
    public List<List<String>> answers = new ArrayList<>();

}
