package com.example.yuer.zahui.db_collection;

import org.litepal.crud.DataSupport;

/**
 * Created by Yuer on 2017/5/22.
 */

public class History{
    private int id; //序号
    private int year;
    private String title;
    private String des;
    private String _id;

    public History(int id, int year, String title, String des, String _id) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.des = des;
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
