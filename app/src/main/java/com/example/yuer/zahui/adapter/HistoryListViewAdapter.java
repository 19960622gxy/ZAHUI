package com.example.yuer.zahui.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.activity.LiShiDetailActivity;
import com.example.yuer.zahui.db_collection.History;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;

import java.util.List;

/**
 * Created by Yuer on 2017/4/14.
 */
//对应用户管理的数据源接受  在侧滑栏
public class HistoryListViewAdapter extends BaseAdapter {
    Context context;
    List<History> historyManager; //全局变量，使他能被使用
    MyDBCollectionHelper dbHelper;


    //需要一个用户信息 泛型  用User表示 用于用户管理
    public HistoryListViewAdapter(Context context, List<History> historyManager) {
        this.context=context;//上下文
        this.historyManager=historyManager;//数据源
    }

    @Override
    public int getCount() {
        return historyManager.size();//这个一定要实现一下,还有getView，其他的可以不怎么用,
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //使用item布局文件 生成对应的view        inflate()将布局文件填充到view中
        View view= LayoutInflater.from(context).inflate(R.layout.item_history,parent,false);

        //将textview中的值设置为动态的
        TextView tvYear= (TextView) view.findViewById(R.id.tv_item_db_year);
        TextView tvTitle=(TextView) view.findViewById(R.id.tv_item_db_title);
        TextView tvDes=(TextView) view.findViewById(R.id.tv_item_db_des);
        final ImageView aixin=(ImageView) view.findViewById(R.id.db_love_history_id);
//        final LinearLayout item=(LinearLayout) view.findViewById(R.id.db_xiangqing);
         //给值，先要有一个user对象
        final History history=historyManager.get(position);
//        把控件附上数据源的值
        tvYear.setText(history.getYear()+"");
        tvTitle.setText(history.getTitle());
        tvDes.setText(history.getDes());
        dbHelper=new MyDBCollectionHelper(context,"MyDBCollection.db",null,1);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();

        aixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete("History","historyId = ?",new String[]{history.get_id()+""});

                historyManager.remove(position);
                notifyDataSetChanged();//刷新一下

                aixin.setImageResource(R.mipmap.joke_collection_normal);
                Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
            }
        });
//        item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context,LiShiDetailActivity.class);
//                context.startActivity(intent);
//            }
//        });

        return view;
    }
}
