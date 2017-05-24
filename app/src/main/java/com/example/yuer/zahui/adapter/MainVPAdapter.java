package com.example.yuer.zahui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class MainVPAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    public MainVPAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "头条";
            case 1:
                return "社会";
            case 2:
                return "国内";
            case 3:
                return "国际";
            case 4:
                return "娱乐";
            case 5:
                return "体育";
            case 6:
                return "军事";
            case 7:
                return "科技";
            case 8:
                return "财经";
            case 9:
                return "时尚";
        }
        return super.getPageTitle(position);
    }
}
