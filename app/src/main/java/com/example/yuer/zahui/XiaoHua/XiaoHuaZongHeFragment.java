package com.example.yuer.zahui.XiaoHua;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.activity.QuTuActivity;
import com.example.yuer.zahui.activity.XiaoHuaActivity;
import com.example.yuer.zahui.activity.YaoYiYaoActivity;
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

public class XiaoHuaZongHeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_xiaohuazonghe,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
        LinearLayout llXiaoHua=(LinearLayout) view.findViewById(R.id.zuixinxiaohua);
        LinearLayout llQuTu=(LinearLayout) view.findViewById(R.id.zuixinqutu);
        LinearLayout llYaoBai=(LinearLayout) view.findViewById(R.id.ll_yaobaiyixia);

        llXiaoHua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),XiaoHuaActivity.class);
//                startActivity(intent);
                getContext().startActivity(intent);
            }
        });

        llQuTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),QuTuActivity.class);
//                startActivity(intent);
                getContext().startActivity(intent);
            }
        });

        llYaoBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), YaoYiYaoActivity.class);
//                startActivity(intent);
                getContext().startActivity(intent);
            }
        });

    }


}
