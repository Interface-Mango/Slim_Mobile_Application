package com.example.hyejin.slimtest;

import java.io.Serializable;

/**
 * Created by hyejin on 2016-08-08.
 */
public class list_item_advice_note implements Serializable {
    private String advice_note_id;
    private String advice_std_num;
    private String advice_sub_num;
    private String advice_title;
    private String advice_content;
    private String advice_date;

    public list_item_advice_note(String advice_note_id, String advice_std_num, String advice_sub_num, String advice_title, String advice_content, String advice_date) {
        this.advice_note_id = advice_note_id;
        this.advice_std_num = advice_std_num;
        this.advice_sub_num = advice_sub_num;
        this.advice_title = advice_title;
        this.advice_content = advice_content;
        this.advice_date = advice_date;
    }

    public String getAdvice_note_id() {
        return advice_note_id;
    }

    public void setAdvice_note_id(String advice_note_id) {
        this.advice_note_id = advice_note_id;
    }

    public String getAdvice_std_num() {
        return advice_std_num;
    }

    public void setAdvice_std_num(String advice_std_num) {
        this.advice_std_num = advice_std_num;
    }

    public String getAdvice_sub_num() {
        return advice_sub_num;
    }

    public void setAdvice_sub_num(String advice_sub_num) {
        this.advice_sub_num = advice_sub_num;
    }

    public String getAdvice_title() {
        return advice_title;
    }

    public void setAdvice_title(String advice_title) {
        this.advice_title = advice_title;
    }

    public String getAdvice_content() {
        return advice_content;
    }

    public void setAdvice_content(String advice_content) {
        this.advice_content = advice_content;
    }

    public String getAdvice_date() {
        return advice_date;
    }

    public void setAdvice_date(String advice_date) {
        this.advice_date = advice_date;
    }


    }



