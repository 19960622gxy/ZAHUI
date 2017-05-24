package com.example.yuer.zahui.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
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
import com.example.yuer.zahui.bean.XiaoHuaResponse;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class XiaoHuaRVAdapter extends RecyclerView.Adapter<XiaoHuaRVAdapter.ViewHolder> {
    //1. 数据源 2.item布局
    Context context;
    List<XiaoHuaResponse.ResultBean.DataBean> resultList;
    MyDBCollectionHelper dbHelper;//要使用这个类 先创建这个对象 再去onCreate中实例化
    private boolean isCol=true;//是否已经收藏

    public XiaoHuaRVAdapter(Context context,List<XiaoHuaResponse.ResultBean.DataBean> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_xiaohua,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //绑定数据

//        final TouTiaoResponse.ResultBean toutiaos = resultList.get(position);
        final XiaoHuaResponse.ResultBean.DataBean xiaohuas = resultList.get(position);

        String pubTime = xiaohuas.getUpdatetime(); //转换成 xx小时前
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
                        else
                        {
                            holder.time.setText("刚刚");
                        }
                    }
                }
            }
        }
       holder.body.setText(xiaohuas.getContent());

        dbHelper=new MyDBCollectionHelper(context,"MyDBCollection.db",null,1);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();//数据库有则拿到 无则创建并拿到
        holder.love.setImageResource(R.mipmap.side_favourite);


        //连接数据库 判断 已存在这个 id
        //取数据库中数据  查询有没有这个用户
        Cursor cursor= db.query("Xiaohua",null,"XiaohuahashId = ?",new String[]{xiaohuas.getHashId()},null,null,null);
        if (cursor.moveToFirst())
        {
            holder.love.setImageResource(R.mipmap.collection_selected);
            isCol=true;
            if (isCol=true)
            {
                holder.love.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "不要再收藏了", Toast.LENGTH_SHORT).show();
                        db.delete("Xiaohua","XiaohuahashId = ?",new String[]{xiaohuas.getHashId()+""});
                        notifyDataSetChanged();
                        holder.love.setImageResource(R.mipmap.side_favourite);
                        Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
        else
        {
            holder.love.setImageResource(R.mipmap.side_favourite);
            isCol=false;
            holder.love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.love.setImageResource(R.mipmap.collection_selected);

                    //存到
                    //插入数据

                    ContentValues values = new ContentValues();
                    String Xiaohuatime = xiaohuas.getUpdatetime();
                    String Xiaohuacontent = xiaohuas.getContent();
                    final String XiaohuahashId = xiaohuas.getHashId();
                    values.put("Xiaohuatime", Xiaohuatime);
                    values.put("Xiaohuacontent", Xiaohuacontent);
                    values.put("XiaohuahashId", XiaohuahashId);
                    //中间还是有其他的数据项，可后面再添加，需要注意，要在布局先修改，再到这边

                    long id = db.insert("Xiaohua", null, values);
                    if (id != -1) {
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                    }


                    if (isCol=true)
                    {
                        holder.love.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.delete("Xiaohua","XiaohuahashId = ?",new String[]{xiaohuas.getHashId()+""});
                                notifyDataSetChanged();
                                holder.love.setImageResource(R.mipmap.side_favourite);
                                Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

        }





    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView body;
        ImageView love;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tv_item_xiaohua_time);
            body=(TextView) itemView.findViewById(R.id.tv_item_xiaohua_content);
            love=(ImageView) itemView.findViewById(R.id.love_xiaohua);
            item = (LinearLayout) itemView;

        }
    }
}