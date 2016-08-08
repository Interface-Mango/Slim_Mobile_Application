package com.example.hyejin.slimtest;

import java.io.Serializable;

/**
 * Created by hyejin on 2016-08-08.
 */
public class list_item_myque implements Serializable{

    private String myque_id;
    private String myque_std_num;
    private String myque_sub_num;
    private String myque_content;
    private String myque_date;

    public list_item_myque(String myque_id, String myque_std_num, String myque_sub_num, String myque_content, String myque_date) {
        this.myque_id = myque_id;
        this.myque_std_num = myque_std_num;
        this.myque_sub_num = myque_sub_num;
        this.myque_content = myque_content;
        this.myque_date = myque_date;
    }

    public String getMyque_id() {
        return myque_id;
    }

    public void setMyque_id(String myque_id) {
        this.myque_id = myque_id;
    }

    public String getMyque_std_num() {
        return myque_std_num;
    }

    public void setMyque_std_num(String myque_std_num) {
        this.myque_std_num = myque_std_num;
    }

    public String getMyque_sub_num() {
        return myque_sub_num;
    }

    public void setMyque_sub_num(String myque_sub_num) {
        this.myque_sub_num = myque_sub_num;
    }

    public String getMyque_content() {
        return myque_content;
    }

    public void setMyque_content(String myque_content) {
        this.myque_content = myque_content;
    }

    public String getMyque_date() {
        return myque_date;
    }

    public void setMyque_date(String myque_date) {
        this.myque_date = myque_date;
    }


}
