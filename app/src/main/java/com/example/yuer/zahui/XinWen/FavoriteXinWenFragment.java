package com.example.yuer.zahui.XinWen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.activity.TouTiaoDetailActivity;
import com.example.yuer.zahui.adapter.HistoryListViewAdapter;
import com.example.yuer.zahui.adapter.NewsListViewAdapter;
import com.example.yuer.zahui.db_collection.History;
import com.example.yuer.zahui.db_collection.News;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuer on 2017/5/22.
 */

public class FavoriteXinWenFragment extends Fragment {
    ListView lvNews;
    NewsListViewAdapter adapter;
    List<News> newsManager; //全局变量，使他能被使用
    //取活的数据源要使用MyDBHelper
    MyDBCollectionHelper dbHelper;
    private String newsURl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_xinwen_favorite,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvNews=(ListView) view.findViewById(R.id.lvNews);
        dbHelper=new MyDBCollectionHelper(getContext(),"MyDBCollection.db",null,1);

        newsManager = new ArrayList<>();
        adapter=new NewsListViewAdapter(getContext(),newsManager);
        lvNews.setAdapter(adapter);

        initData();
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(), "sssss", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getContext(), TouTiaoDetailActivity.class);
                intent.putExtra("url", newsManager.get(i).getUrl());  //传递给下一个activity
                getContext().startActivity(intent);
            }
        });
    }

    private void initData() {
        //取所有数据

        //取数据库中的用户信息  添加到集合中
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor= db.query("News",null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            //通过列号取值
            int id=cursor.getInt(0);
            newsURl = cursor.getString(1);
            String newsTitle=cursor.getString(2);
            String newsAuthorName=cursor.getString(3);
            String newsPicOne=cursor.getString(4);
            String newsPicTwo=cursor.getString(5);
            String newsPicThr=cursor.getString(6);
            String newsDate=cursor.getString(7);
            String newsUniqueKey=cursor.getString(8);



            //拿到一个对象
//   死数据源进行测试        User user=new User(1,"hulin","112523","112523","18363456833","女","狐狸","竭尽全力...");
            News news=new News(id, newsURl,newsTitle,newsAuthorName,newsPicOne,newsPicTwo,newsPicThr,newsDate,newsUniqueKey);
            //将对象添加到集合中
            newsManager.add(news);
        }
        adapter.notifyDataSetChanged();//刷新listview的adapter

    }



}
