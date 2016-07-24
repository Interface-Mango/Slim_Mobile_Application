package com.example.hyejin.slimtest;

/**
 * Created by hyejin on 2016-07-07.
 */
public class list_item {
    private String num;
    private String title;
    private String date;
    private String location;
    private String lecturer_name;


    public list_item(String num, String title, String date, String location, String lecturer_name) {
        this.num = num;
        this.title = title;
        this.date = date;
        this.location = location;
        this.lecturer_name = lecturer_name;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
