package com.example.yuer.zahui.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.db_collection.History;
import com.example.yuer.zahui.db_collection.News;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yuer on 2017/4/14.
 */
//对应用户管理的数据源接受  在侧滑栏
public class NewsListViewAdapter extends BaseAdapter {
    Context context;
    List<News> newsManager; //全局变量，使他能被使用
    MyDBCollectionHelper dbHelper;


    //需要一个用户信息 泛型  用User表示 用于用户管理
    public NewsListViewAdapter(Context context,List<News> newsManager) {
        this.context=context;//上下文
        this.newsManager=newsManager;//数据源
    }

    @Override
    public int getCount() {
        return newsManager.size();//这个一定要实现一下,还有getView，其他的可以不怎么用,
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
        View view= LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);

        //将textview中的值设置为动态的
        TextView tvTitle= (TextView) view.findViewById(R.id.tv_item_love_biaoti);
        ImageView  image1=(ImageView) view.findViewById(R.id.love_image1);
        ImageView  image2=(ImageView) view.findViewById(R.id.love_image2);
        ImageView  image3=(ImageView) view.findViewById(R.id.love_image3);
        TextView tvAuthor=(TextView) view.findViewById(R.id.tv_item_love_author);
        TextView tvDate=(TextView) view.findViewById(R.id.tv_item_love_time);
        final ImageView aixin=(ImageView) view.findViewById(R.id.love_xinwen_favorite);
//        final LinearLayout item=(LinearLayout) view.findViewById(R.id.db_xiangqing);
         //给值，先要有一个user对象
        final News news=newsManager.get(position);
//        把控件附上数据源的值
        tvTitle.setText(news.getTitle());


        final String i1=news.getThumbnail_pic_s();
        final String i2=news.getThumbnail_pic_s02();
        final String i3=news.getThumbnail_pic_s03();
        if (i1!=null)
        {
            image1.setVisibility(View.VISIBLE);
            Picasso.with(context).load(i1)
                    .placeholder(R.color.colorPrimary)
                    .into(image1);
        }
        if (i2!=null)
        {
            image2.setVisibility(View.VISIBLE);
            Picasso.with(context).load(i2)
                    .placeholder(R.color.colorPrimary)
                    .into(image2);
        }
        if (i3!=null)
        {
            image3.setVisibility(View.VISIBLE);
            Picasso.with(context).load(i3)
                    .placeholder(R.color.colorPrimary)
                    .into(image3);
        }

        tvAuthor.setText("@"+news.getAuthor_name());
        tvDate.setText(news.getDate());

        dbHelper=new MyDBCollectionHelper(context,"MyDBCollection.db",null,1);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();

        aixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete("News","newsUniqueKey = ?",new String[]{news.getUniquekey()+""});

                newsManager.remove(position);
                notifyDataSetChanged();//刷新一下

                aixin.setImageResource(R.mipmap.side_favourite);
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
