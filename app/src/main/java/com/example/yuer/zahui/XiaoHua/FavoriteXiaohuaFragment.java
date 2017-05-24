package com.example.yuer.zahui.XiaoHua;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.adapter.HistoryListViewAdapter;
import com.example.yuer.zahui.adapter.XiaohuaListViewAdapter;
import com.example.yuer.zahui.db_collection.History;
import com.example.yuer.zahui.db_collection.Xiaohua;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuer on 2017/5/22.
 */

public class FavoriteXiaohuaFragment extends Fragment {
    ListView lvXiaohua;
    XiaohuaListViewAdapter adapter;
    List<Xiaohua> xiaohuaManager; //全局变量，使他能被使用
    //取活的数据源要使用MyDBHelper
    MyDBCollectionHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_xiaohua_favorite,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvXiaohua=(ListView) view.findViewById(R.id.lvXiaohua);
        dbHelper=new MyDBCollectionHelper(getContext(),"MyDBCollection.db",null,1);

        xiaohuaManager = new ArrayList<>();
        adapter=new XiaohuaListViewAdapter(getContext(),xiaohuaManager);
        lvXiaohua.setAdapter(adapter);
        initData();
    }

    private void initData() {
        //取所有数据

        //取数据库中的用户信息  添加到集合中
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor= db.query("Xiaohua",null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            //通过列号取值
            int id=cursor.getInt(0);
            String Xiaohuatime=cursor.getString(1);
            String Xiaohuacontent=cursor.getString(2);
            String XiaohuahashId=cursor.getString(3);


            //拿到一个对象
//   死数据源进行测试        User user=new User(1,"hulin","112523","112523","18363456833","女","狐狸","竭尽全力...");
            Xiaohua xiaohua=new Xiaohua(id,Xiaohuatime,Xiaohuacontent,XiaohuahashId);
            //将对象添加到集合中
            xiaohuaManager.add(xiaohua);
        }
        adapter.notifyDataSetChanged();//刷新listview的adapter

    }



}
