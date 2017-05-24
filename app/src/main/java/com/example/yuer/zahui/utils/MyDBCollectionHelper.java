package com.example.yuer.zahui.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yuer on 2017/4/13.
 */

public class MyDBCollectionHelper extends SQLiteOpenHelper {

    //常量定义数据库语句
    public static final String CREATE_HISTORY="CREATE TABLE History(id integer" +
                          " primary key autoincrement,historyYear integer,historyTitle text,historyDes text" +
                          ",historyId text)";
    public static final String CREATE_NEWS="CREATE TABLE News(id integer" +
            " primary key autoincrement,newsURl text,newsTitle text,newsAuthorName text,newsPicOne text" +
            ",newsPicTwo text,newsPicThr text,newsDate text,newsUniqueKey text)";

    public static final String CREATE_XIAOHUA="CREATE TABLE Xiaohua(id integer" +
            " primary key autoincrement,Xiaohuatime text,Xiaohuacontent text,XiaohuahashId text)";

    public static final String CREATE_QUTU="CREATE TABLE Qutu(id integer" +
            " primary key autoincrement,qutuTime text,qutuContent text,qutuUrl text" +
            ",qutuHashId text)";


    public MyDBCollectionHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建历史表
        db.execSQL(CREATE_HISTORY);
        //创建新闻表
        db.execSQL(CREATE_NEWS);
         //创建笑话表
        db.execSQL(CREATE_XIAOHUA);
        //创建趣图表
        db.execSQL(CREATE_QUTU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
     //数据库升级
//        db.equals("drop table if exist UserInfo");
        db.execSQL("drop table if exists History");//删除之前的表再重新创建一下
        db.execSQL("drop table if exists News");//删除之前的表再重新创建一下
        db.execSQL("drop table if exists Xiaohua");//删除之前的表再重新创建一下
        db.execSQL("drop table if exists Qutu");//删除之前的表再重新创建一下

        onCreate(db);//需要升级时要修改版本号1变2 一次叠加

    }
}
