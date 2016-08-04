package com.example.hyejin.slimtest;

import java.io.Serializable;

/**
 * Created by hyejin on 2016-08-04.
 */
public class list_item_notice implements Serializable {
    public String num;
    public String title;
    public String content;
    private String sub_num;
    private String date;



    public list_item_notice(String num, String title, String content, String sub_num, String date) {
        this.num = num;
        this.title = title;
        this.content = content;
        this.sub_num = sub_num;
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_num() {
        return sub_num;
    }

    public void setSub_num(String sub_num) {
        this.sub_num = sub_num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    }





