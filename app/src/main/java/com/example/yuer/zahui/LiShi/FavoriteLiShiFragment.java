package com.example.yuer.zahui.LiShi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.activity.LiShiDetailActivity;
import com.example.yuer.zahui.adapter.HistoryListViewAdapter;
import com.example.yuer.zahui.adapter.LiShiRVAdapter;
import com.example.yuer.zahui.db_collection.History;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Yuer on 2017/5/22.
 */

public class FavoriteLiShiFragment extends Fragment {
        ListView lvHistoryToday;
    HistoryListViewAdapter adapter;
    List<History> historyManager; //全局变量，使他能被使用
    //取活的数据源要使用MyDBHelper
    MyDBCollectionHelper dbHelper;
    private String historyId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_lishi_favorite,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvHistoryToday=(ListView) view.findViewById(R.id.lvHistoryToday);
        dbHelper=new MyDBCollectionHelper(getContext(),"MyDBCollection.db",null,1);

        historyManager = new ArrayList<>();
        adapter=new HistoryListViewAdapter(getContext(),historyManager);
        lvHistoryToday.setAdapter(adapter);
        initData();

        lvHistoryToday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),LiShiDetailActivity.class);
//                //携带id参数

                intent.putExtra("id",historyManager.get(i).get_id());  //传递给下一个activity
                getContext().startActivity(intent);
            }
        });
    }

    private void initData() {
        //取所有数据

        //取数据库中的用户信息  添加到集合中
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor= db.query("History",null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            //通过列号取值
            int id=cursor.getInt(0);
            int historyYear=cursor.getInt(1);
            String historyTitle=cursor.getString(2);
            String historyDes=cursor.getString(3);
            historyId = cursor.getString(4);


            //拿到一个对象
//   死数据源进行测试        User user=new User(1,"hulin","112523","112523","18363456833","女","狐狸","竭尽全力...");
            History history=new History(id,historyYear,historyTitle,historyDes, historyId);
            //将对象添加到集合中
            historyManager.add(history);
        }
        adapter.notifyDataSetChanged();//刷新listview的adapter

    }



}
