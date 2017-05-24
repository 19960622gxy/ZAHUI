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
import com.example.yuer.zahui.db_collection.News;
import com.example.yuer.zahui.db_collection.Qutu;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuer on 2017/4/14.
 */
//对应用户管理的数据源接受  在侧滑栏
public class QutuListViewAdapter extends BaseAdapter {
    Context context;
    List<Qutu> qutuManager; //全局变量，使他能被使用
    MyDBCollectionHelper dbHelper;


    //需要一个用户信息 泛型  用User表示 用于用户管理
    public QutuListViewAdapter(Context context, List<Qutu> qutuManager) {
        this.context=context;//上下文
        this.qutuManager=qutuManager;//数据源
    }

    @Override
    public int getCount() {
        return qutuManager.size();//这个一定要实现一下,还有getView，其他的可以不怎么用,
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
        View view= LayoutInflater.from(context).inflate(R.layout.item_qutu_favorite,parent,false);

        //将textview中的值设置为动态的
        TextView tvTime= (TextView) view.findViewById(R.id.tv_item_qutu_love_time);

        TextView tvContent=(TextView) view.findViewById(R.id.tv_item_qutu_love_content);
        ImageView Image=(ImageView) view.findViewById(R.id.iv_item_qutu_love_image);
        final ImageView aixin=(ImageView) view.findViewById(R.id.love_qutu_favorite);
//        final LinearLayout item=(LinearLayout) view.findViewById(R.id.db_xiangqing);
         //给值，先要有一个user对象
        final Qutu qutu=qutuManager.get(position);
//        把控件附上数据源的值
       tvContent.setText(qutu.getContent());

        String pubTime = qutu.getUpdatetime(); //转换成 xx小时前
        SimpleDateFormat a=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Date dateNow=new Date();
        try {
            date=a.parse(pubTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar=Calendar.getInstance();
        Calendar calendarNow=Calendar.getInstance();
        calendar.setTime(date);
        calendarNow.setTime(dateNow);
        int years=calendarNow.get(Calendar.YEAR)-calendar.get(Calendar.YEAR);
        int months=calendarNow.get(Calendar.MONTH)-calendar.get(Calendar.MONTH);
        int days=calendarNow.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_MONTH);
        int hours=calendarNow.get(Calendar.HOUR_OF_DAY)-calendar.get(Calendar.HOUR_OF_DAY);
        int minutes=calendarNow.get(Calendar.MINUTE)-calendar.get(Calendar.MINUTE);
        int seconds=calendarNow.get(Calendar.SECOND)-calendar.get(Calendar.SECOND);
        if (years>0)
        {
            tvTime.setText(years+"年前");
        }
        else
        {
            if (months>0)
            {
                tvTime.setText(months+"月前");
            }
            else
            {
                if (days>0)
                {
                    tvTime.setText(days+"日前");
                }
                else
                {
                    if (hours>0)
                    {
                        tvTime.setText(hours+"小时前");
                    }
                    else
                    {
                        if (minutes>0)
                        {
                            tvTime.setText(minutes+"分钟前");
                        }
                        else
                        {
                            tvTime.setText("刚刚");
                        }
                    }
                }
            }
        }




        final String i1=qutu.getUrl();
        if (i1!=null)
        {
            Picasso.with(context).load(i1)
                    .placeholder(R.color.colorPrimary)
                    .into(Image);
        }



        dbHelper=new MyDBCollectionHelper(context,"MyDBCollection.db",null,1);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();

        aixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete("Qutu","qutuHashId = ?",new String[]{qutu.getHashId()+""});

                qutuManager.remove(position);
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
