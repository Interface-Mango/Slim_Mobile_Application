package com.example.hyejin.slimtest;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hyejin on 2016-07-07.
 */
public class list_item_attend implements Serializable {
    private String attend_id;
    private String sub_num;
    private String std_num;
    private String attend_time;
    private String attend_check;

    public list_item_attend(String attend_id, String sub_num, String std_num, String attend_time, String attend_check) {
        this.attend_id = attend_id;
        this.sub_num = sub_num;
        this.std_num = std_num;
        this.attend_time = attend_time;
        this.attend_check = attend_check;

    }


    public String getAttend_time() {
        return attend_time;
    }

    public void setAttend_time(String attend_time) {
        this.attend_time = attend_time;
    }

    public String getAttend_id() {
        return attend_id;
    }

    public void setAttend_id(String attend_id) {
        this.attend_id = attend_id;
    }

    public String getSub_num() {
        return sub_num;
    }

    public void setSub_num(String sub_num) {
        this.sub_num = sub_num;
    }

    public String getStd_num() {
        return std_num;
    }

    public void setStd_num(String std_num) {
        this.std_num = std_num;
    }

    public String getAttend_check() {

        return attend_check;}

    public void setAttend_check(String attend_check) {
        this.attend_check = attend_check;
    }














}
