package com.example.yuer.zahui.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.LiShi.LiShiFragment;
import com.example.yuer.zahui.R;
import com.example.yuer.zahui.activity.LiShiDetailActivity;
import com.example.yuer.zahui.bean.LiShiResponse;
import com.example.yuer.zahui.db_collection.History;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import org.litepal.crud.DataSupport;

import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class LiShiRVAdapter extends RecyclerView.Adapter<LiShiRVAdapter.ViewHolder> {
    //1. 数据源 2.item布局
    Context context;
    List<LiShiResponse.ResultBean> resultBeanList;
    MyDBCollectionHelper dbHelper;//要使用这个类 先创建这个对象 再去onCreate中实例化
    private boolean isCol=true;//是否已经收藏
//    HistoryListViewAdapter adapter;


    public LiShiRVAdapter(Context context,  List<LiShiResponse.ResultBean> resultBeanList) {
        this.context = context;
        this.resultBeanList = resultBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_lishi,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //绑定数据

        final LiShiResponse.ResultBean lishis = resultBeanList.get(position);
          holder.timeY.setText(lishis.getYear()+"");
//          holder.timeM.setText(lishis.getMonth()+"");
//          holder.timeD.setText(lishis.getDay()+"");
//          holder.time2.setText(lishis.getLunar());
          holder.title.setText(lishis.getTitle());
          holder.body.setText(lishis.getDes());


        dbHelper=new MyDBCollectionHelper(context,"MyDBCollection.db",null,1);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();//数据库有则拿到 无则创建并拿到
        holder.love.setImageResource(R.mipmap.joke_collection_normal);


        //连接数据库 判断 已存在这个 id
        //取数据库中数据  查询有没有这个用户
        Cursor cursor= db.query("History",null,"historyId = ?",new String[]{lishis.get_id()},null,null,null);
        if (cursor.moveToFirst())
        {
            holder.love.setImageResource(R.mipmap.love);
            isCol=true;
            if (isCol=true)
            {
                holder.love.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "不要再收藏了", Toast.LENGTH_SHORT).show();
                        db.delete("History","historyId = ?",new String[]{lishis.get_id()+""});
                        notifyDataSetChanged();
                        holder.love.setImageResource(R.mipmap.joke_collection_normal);
                        Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
        else
        {
            holder.love.setImageResource(R.mipmap.joke_collection_normal);
            isCol=false;
            holder.love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.love.setImageResource(R.mipmap.love);

                    //存到
                    //插入数据

                    ContentValues values = new ContentValues();
                    int historyYear = lishis.getYear();
                    String historyTitle = lishis.getTitle();
                    String historyDes = lishis.getDes();
                    final String historyId = lishis.get_id();
                    values.put("historyYear", historyYear);
                    values.put("historyTitle", historyTitle);
                    values.put("historyDes", historyDes);
                    values.put("historyId", historyId);
                    //中间还是有其他的数据项，可后面再添加，需要注意，要在布局先修改，再到这边

                    long id = db.insert("History", null, values);
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
                                db.delete("History","historyId = ?",new String[]{lishis.get_id()+""});
                                notifyDataSetChanged();
                                holder.love.setImageResource(R.mipmap.joke_collection_normal);
                                Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

        }




//            holder.love.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    holder.love.setImageResource(R.mipmap.love);
//
//                    //存到
//                    //插入数据
//
//                    ContentValues values = new ContentValues();
//                    int historyYear = lishis.getYear();
//                    String historyTitle = lishis.getTitle();
//                    String historyDes = lishis.getDes();
//                    final String historyId = lishis.get_id();
//                    values.put("historyYear", historyYear);
//                    values.put("historyTitle", historyTitle);
//                    values.put("historyDes", historyDes);
//                    values.put("historyId", historyId);
//                    //中间还是有其他的数据项，可后面再添加，需要注意，要在布局先修改，再到这边
//
//                    long id = db.insert("History", null, values);
//                    if (id != -1) {
//                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                    if (isCol=true)
//                    {
//                        holder.love.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(context, "已经收藏过了", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            });



        //点击监听  跳转到对应详情页
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
//                Toast.makeText(context,"ID= "+lishis.get_id(),Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context,LiShiDetailActivity.class);
//                //携带id参数
                intent.putExtra("id",lishis.get_id());  //传递给下一个activity
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return resultBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView timeY,timeM,timeD;
//        TextView time2;
        TextView title;
        TextView body;
        ImageView image;
        TextView timeY;
//        CardView item;
        LinearLayout item;
        ImageView love;
        public ViewHolder(View itemView) {
            super(itemView);

            title=(TextView) itemView.findViewById(R.id.tv_item_lishi_title);
            body=(TextView) itemView.findViewById(R.id.tv_item_lishi_des);
//            image=(ImageView) itemView.findViewById(R.id.lishi_image);
            timeY = (TextView) itemView.findViewById(R.id.tv_item_lishi_yeartime);
            love=(ImageView) itemView.findViewById(R.id.love_history);
//            timeM = (TextView) itemView.findViewById(R.id.tv_item_lishi_monthtime);
//            timeD = (TextView) itemView.findViewById(R.id.tv_item_lishi_daytime);
//            time2 = (TextView) itemView.findViewById(R.id.tv_item_lishi_yinlitime);
//            item = (CardView) itemView;
              item=(LinearLayout) itemView.findViewById(R.id.xiangqing);

        }
    }
}