package com.example.hyejin.slimtest;

import java.util.Date;

/**
 * Created by hyejin on 2016-07-07.
 */
public class list_item {
    private String num;
    private String title;
    private Date date;



    public list_item(String num, String title, Date date) {
        this.num = num;
        this.title = title;
        this.date = date;
    }


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
