package com.example.yuer.zahui.XinWen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.adapter.TouTiaoRVAdapter;
import com.example.yuer.zahui.bean.TouTiaoResponse;
import com.example.yuer.zahui.utils.Api;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Yuer on 2017/5/2.
 */

public class CaiJingFragment extends Fragment {
    private static final String TAG = "SheHuiFragment";
//
    List<TouTiaoResponse.ResultBean.DataBean> resultList;
    TouTiaoRVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_toutiao,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SpringView springView= (SpringView) view.findViewById(R.id.spring_toutiao);
        springView.setHeader(new DefaultHeader(getContext())); //设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下拉 刷新数据
                resultList.clear();//清空一次
//                pageIndex=1;
                getData();
            }

            @Override
            public void onLoadmore() {
                //上拉 加载数据
//                pageIndex++;
                getData();  //如何加载所有 没有index
//                Toast.makeText(getContext(),"没有更多头条数据了",Toast.LENGTH_SHORT).show();

            }
        });




        //构建技术问答列表
        resultList = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_toutiao);
        adapter  = new TouTiaoRVAdapter(getContext(),resultList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
//
        getData();
    }

    private void getData() {

        OkGo.get(Api.url)
                .tag(this)
                .params("key",Api.NEWS_APPKEY)
                .params("type","caijing")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
//////                        s=s.replaceAll("https://static.oschina.net/","https://www.oschina.net/");
//////                        s=s.replaceAll("\"\""," {\"name\": \"default\",\"time\": \"1970-03-22 21:25:51\"}");
                        TouTiaoResponse toutiaoResponse = gson.fromJson(s,TouTiaoResponse.class);
                          List<TouTiaoResponse.ResultBean.DataBean> data=toutiaoResponse.getResult().getData();
                        resultList.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });

    }

}
