package com.example.yuer.zahui.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.activity.TouTiaoDetailActivity;
import com.example.yuer.zahui.bean.TouTiaoResponse;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class TouTiaoRVAdapter extends RecyclerView.Adapter<TouTiaoRVAdapter.ViewHolder> {
    //1. 数据源 2.item布局
    Context context;
    List<TouTiaoResponse.ResultBean.DataBean> resultList;
    MyDBCollectionHelper dbHelper;//要使用这个类 先创建这个对象 再去onCreate中实例化
    private boolean isCol=true;//是否已经收藏
//    HistoryListViewAdapter adapter;
    public TouTiaoRVAdapter(Context context,  List<TouTiaoResponse.ResultBean.DataBean> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_toutiao,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //绑定数据

//        final TouTiaoResponse.ResultBean toutiaos = resultList.get(position);
        final TouTiaoResponse.ResultBean.DataBean toutiaos = resultList.get(position);

        String pubTime = toutiaos.getDate(); //转换成 xx小时前
        SimpleDateFormat a=new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
            holder.time.setText(years+"年前");
        }
        else
        {
            if (months>0)
            {
                holder.time.setText(months+"月前");
            }
            else
            {
                if (days>0)
                {
                    holder.time.setText(days+"日前");
                }
                else
                {
                    if (hours>0)
                    {
                        holder.time.setText(hours+"小时前");
                    }
                    else
                    {
                        if (minutes>0)
                        {
                            holder.time.setText(minutes+"分钟前");
                        }
//                        else
//                        {
//                            holder.time.setText("刚刚");
//                        }
                    }
                }
            }
        }


//        holder.toutiao.setText(toutiaos.getCategory());
//        holder.toutiao.setVisibility(View.VISIBLE);
        holder.title.setText(toutiaos.getTitle());
        holder.author.setText("@"+toutiaos.getAuthor_name());

        final String i1=toutiaos.getThumbnail_pic_s();
        final String i2=toutiaos.getThumbnail_pic_s02();
        final String i3=toutiaos.getThumbnail_pic_s03();
        if (i1!=null)
        {
            holder.image1.setVisibility(View.VISIBLE);
            Picasso.with(context).load(i1)
                    .placeholder(R.color.colorPrimary)
                    .into(holder.image1);
        }
        if (i2!=null)
        {
            holder.image2.setVisibility(View.VISIBLE);
            Picasso.with(context).load(i2)
                    .placeholder(R.color.colorPrimary)
                    .into(holder.image2);
        }
        if (i3!=null)
        {
            holder.image3.setVisibility(View.VISIBLE);
            Picasso.with(context).load(i3)
                    .placeholder(R.color.colorPrimary)
                    .into(holder.image3);
        }

        //收藏事件
//        holder.xinwenFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.xinwenFavorite.setImageResource(R.mipmap.collection_selected);
//            }
//        });
        dbHelper=new MyDBCollectionHelper(context,"MyDBCollection.db",null,1);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();//数据库有则拿到 无则创建并拿到
        holder.xinwenFavorite.setImageResource(R.mipmap.side_favourite);


        //连接数据库 判断 已存在这个 id
        //取数据库中数据  查询有没有这个用户
        Cursor cursor= db.query("News",null,"newsUniqueKey = ?",new String[]{toutiaos.getUniquekey()},null,null,null);
        if (cursor.moveToFirst())
        {
            holder.xinwenFavorite.setImageResource(R.mipmap.collection_selected);
            isCol=true;
            if (isCol=true)
            {
                holder.xinwenFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "不要再收藏了", Toast.LENGTH_SHORT).show();
                        db.delete("News","newsUniqueKey = ?",new String[]{toutiaos.getUniquekey()+""});
                        notifyDataSetChanged();
                        holder.xinwenFavorite.setImageResource(R.mipmap.side_favourite);
                        Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
        else
        {
            holder.xinwenFavorite.setImageResource(R.mipmap.side_favourite);
            isCol=false;
            holder.xinwenFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.xinwenFavorite.setImageResource(R.mipmap.collection_selected);

                    //存到
                    //插入数据

                    ContentValues values = new ContentValues();
                    String newsURl = toutiaos.getUrl();
                    String newsTitle = toutiaos.getTitle();
                    String newsAuthorName = toutiaos.getAuthor_name();
//                    if (i1!=null) {
                        String newsPicOne = i1;
//                    }
//                    else
//                    {
//                        String newsPicOne="";
//                    }
//                    if (i2!=null) {
                        String newsPicTwo =i2;
//                    }
//                    else
//                    {
//                        String newsPicTwo="";
//                    }
//                    if (i3!=null) {
                        String newsPicThr = i3;
//                    }
//                    else
//                    {
//                        String newsPicThr="";
//                    }
                    String newsDate = toutiaos.getDate();

                    final String newsUniqueKey = toutiaos.getUniquekey();
                    values.put("newsURl", newsURl);
                    values.put("newsTitle", newsTitle);
                    values.put("newsAuthorName", newsAuthorName);
                    values.put("newsPicOne", newsPicOne);
                    values.put("newsPicTwo", newsPicTwo);
                    values.put("newsPicThr", newsPicThr);
                    values.put("newsDate", newsDate);
                    values.put("newsUniqueKey", newsUniqueKey);

                    //中间还是有其他的数据项，可后面再添加，需要注意，要在布局先修改，再到这边

                    long id = db.insert("News", null, values);
                    if (id != -1) {
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                    }


                    if (isCol=true)
                    {
                        holder.xinwenFavorite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.delete("News","newsUniqueKey = ?",new String[]{toutiaos.getUniquekey()+""});
                                notifyDataSetChanged();
                                holder.xinwenFavorite.setImageResource(R.mipmap.side_favourite);
                                Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

        }




















        //点击监听  跳转到对应详情页
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //检测
//                    Toast.makeText(context,"点击了"+news.getId(),Toast.LENGTH_SHORT).show();

                //跳转
                Intent intent=new Intent(context,TouTiaoDetailActivity.class);
                //携带id参数
                intent.putExtra("url",toutiaos.getUrl());  //传递给下一个activity
                context.startActivity(intent);
            }
        });


//        holder.time.setText(pubTime);
//        if (pubTime.equals("昨天")){  //判断 是今天以前的  让隐藏
//            holder.tag.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView toutiao;
        TextView title;
        TextView author;
        ImageView image1,image2,image3,xinwenFavorite;
        TextView time;
        CardView item;
        public ViewHolder(View itemView) {
            super(itemView);
//            toutiao=(TextView) itemView.findViewById(R.id.tv_item_toutiao_biaoqian);
            title=(TextView) itemView.findViewById(R.id.tv_item_toutiao_biaoti);
            author=(TextView) itemView.findViewById(R.id.tv_item_toutiao_author);
            image1=(ImageView) itemView.findViewById(R.id.image1);
            image2=(ImageView) itemView.findViewById(R.id.image2);
            image3=(ImageView) itemView.findViewById(R.id.image3);
            xinwenFavorite=(ImageView) itemView.findViewById(R.id.xinwen_favorite);
            time = (TextView) itemView.findViewById(R.id.tv_item_toutiao_time);
            item = (CardView) itemView;

        }
    }
}