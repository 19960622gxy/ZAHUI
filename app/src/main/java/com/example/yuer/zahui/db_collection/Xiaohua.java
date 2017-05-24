package com.example.yuer.zahui.db_collection;

/**
 * Created by Yuer on 2017/5/23.
 */

public class Xiaohua {

    private int id;//序号
    private String updatetime;
    private String content;
    private String hashId;

    public Xiaohua(int id, String updatetime, String content, String hashId) {
        this.id = id;
        this.updatetime = updatetime;
        this.content = content;
        this.hashId = hashId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }
}
