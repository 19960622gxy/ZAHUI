package com.example.yuer.zahui.bean;

/**
 * Created by Yuer on 2017/4/28.
 */

public class TabBean {
    private  String title;
    private  int selected;
    private  int unSelected;
    private int type; //种类    1 图文 0单图片
    private  int index ;  //下标

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TabBean(String title, int selected, int unSelected, int type) {
        this.title = title;
        this.selected = selected;
        this.unSelected = unSelected;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getUnSelected() {
        return unSelected;
    }

    public void setUnSelected(int unSelected) {
        this.unSelected = unSelected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
