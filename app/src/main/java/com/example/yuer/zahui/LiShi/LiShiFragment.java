package com.example.yuer.zahui.LiShi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.adapter.LiShiRVAdapter;
import com.example.yuer.zahui.adapter.TouTiaoRVAdapter;
import com.example.yuer.zahui.bean.LiShiResponse;
import com.example.yuer.zahui.bean.TouTiaoResponse;
import com.example.yuer.zahui.utils.Api;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Yuer on 2017/5/2.
 */

public class LiShiFragment extends Fragment {

//    List<TouTiaoResponse.ResultBean.DataBean> resultList;
    List<LiShiResponse.ResultBean> resultBeanList;
//    TouTiaoRVAdapter adapter;
    LiShiRVAdapter adapter;
    private int months;
    private int days;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_lishi,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
        SpringView springView= (SpringView) view.findViewById(R.id.spring_lishi);
        springView.setHeader(new DefaultHeader(getContext())); //设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下拉 刷新数据
                resultBeanList.clear();//清空一次
//                pageIndex=1;
                getData(months,days);
            }

            @Override
            public void onLoadmore() {
                //上拉 加载数据
//                pageIndex++;
                resultBeanList.clear();//清空一次
                getData(months,days);  //如何加载所有 没有index
                Toast.makeText(getContext(),"没有更多数据了", Toast.LENGTH_SHORT).show();

            }
        });




        //构建技术问答列表
        resultBeanList = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_lishi);
        adapter  = new LiShiRVAdapter(getContext(),resultBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
//

//
//        long time=System.currentTimeMillis();
//        final Calendar mCalendar=Calendar.getInstance();
//        mCalendar.setTimeInMillis(time);
//         int months=mCalendar.get(Calendar.MONTH);
//        int days=mCalendar.get(Calendar.DAY_OF_MONTH);
        Calendar c = Calendar.getInstance();
        months = c.get(Calendar.MONTH);
        days = c.get(Calendar.DAY_OF_MONTH);

        TextView tvMonth=(TextView) view.findViewById(R.id.month);
        TextView tvDay=(TextView) view.findViewById(R.id.day);
        int MONTH= months +1;
        tvMonth.setText(MONTH+"");
        tvDay.setText(days +"");

        getData(months, days);
    }

    private void getData(int months,int days) {

        //成功后想办法获取系统当天时间
        OkGo.get(Api.HISTORY_HOST)
                .tag(this)
                .params("key",Api.HISTORY_APPKEY)
                .params("v","1.0")
                .params("month",months+1)
                .params("day",days)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
                        LiShiResponse lishiResponse = gson.fromJson(s,LiShiResponse.class);
                        List<LiShiResponse.ResultBean> data=lishiResponse.getResult();
                        resultBeanList.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });

    }

}
