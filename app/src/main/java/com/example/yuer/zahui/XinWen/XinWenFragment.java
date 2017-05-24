package com.example.yuer.zahui.XinWen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuer.zahui.adapter.MainVPAdapter;
import com.example.yuer.zahui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuer on 2017/5/2.
 */

public class XinWenFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //通过布局文件生成对应的view
        View view = inflater.inflate(R.layout.fragment_xinwen,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //有了view，去操作
        //找控件
        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.tablayout_xinwen);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.vp_xinwen);
        //给viewPager设置adapter 重用MainVPAdapter  可以重建
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new TouTiaoFragment());         //头条
        fragmentList.add(new SheHuiFragment());         //社会
        fragmentList.add(new GuoNeiFragment());         //国内
        fragmentList.add(new GuoJiFragment());         //国际
        fragmentList.add(new YuLeFragment());         //娱乐
        fragmentList.add(new TiYuFragment());         //体育
        fragmentList.add(new JunShiFragment());         //军事
        fragmentList.add(new KeJiFragment());         //科技
        fragmentList.add(new CaiJingFragment());         //财经
        fragmentList.add(new ShiShangFragment());         //时尚

        MainVPAdapter adapter=new MainVPAdapter(getFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);//让tabLayout和viewPager两个有关联



    }


}
