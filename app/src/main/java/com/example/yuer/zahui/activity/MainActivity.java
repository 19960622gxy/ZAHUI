package com.example.yuer.zahui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.LiShi.LiShiFragment;
import com.example.yuer.zahui.R;
import com.example.yuer.zahui.TianQi.TianQiFragment;
import com.example.yuer.zahui.XiaoHua.XiaoHuaZongHeFragment;
import com.example.yuer.zahui.XinWen.XinWenFragment;
import com.example.yuer.zahui.adapter.MainVPAdapter;
import com.example.yuer.zahui.bean.TabBean;
import com.example.yuer.zahui.utils.MyDBHelper;
import com.example.yuer.zahui.widget.BottomLayout;
import com.example.yuer.zahui.widget.UnScrollViewPager;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    UnScrollViewPager viewPager;
    MainVPAdapter mainVPAdapter ;
    List<Fragment> fragmentList = new ArrayList<>();
    MyDBHelper dbHelper;
    private String nName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nName=getIntent().getStringExtra("nickName");


        Toolbar toolbar=(Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        getSupportActionBar().setTitle("新闻");


//        //添加 四大模块 fragment
        fragmentList.add(new XinWenFragment());      //新闻
        fragmentList.add(new TianQiFragment());      //天气
        fragmentList.add(new LiShiFragment());         //今天  历史上的今天
        fragmentList.add(new XiaoHuaZongHeFragment());         //笑话
//        fragmentList.add(new QuTuFragment());



        mainVPAdapter = new MainVPAdapter(getSupportFragmentManager(),fragmentList);
        viewPager = (UnScrollViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(mainVPAdapter);
        viewPager.setOffscreenPageLimit(4);  //页数限制 缓存页数是4页


        BottomLayout bottomLayout = (BottomLayout) findViewById(R.id.bottomLayout);
        List<TabBean> tabs = new ArrayList<>();
        tabs.add(new TabBean("新闻",R.mipmap.bottom_news,R.mipmap.bottom_news_1,1));
        tabs.add(new TabBean("天气",R.mipmap.bottom_travel,R.mipmap.bottom_travel_1,1));
        tabs.add(new TabBean("今天",R.mipmap.bottom_ticket,R.mipmap.bottom_ticket_1,1));
        tabs.add(new TabBean("笑话",R.mipmap.bottom_joke,R.mipmap.bottom_joke_1,1));
        bottomLayout.setBottom(this,tabs,viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //切换到哪一页  导航条的文字发生改变
                switch (position)
                {
                    case 0:
                        getSupportActionBar().setTitle("新闻");
                        break;
                    case 1:
                        getSupportActionBar().setTitle("天气");
                        break;
                    case 2:
                        getSupportActionBar().setTitle("历史上的今天");
                        break;
                    case 3:
                        getSupportActionBar().setTitle("笑口常开每一天");
//                        setTitle("我的");
                        //将toobar隐藏
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });















        mDrawerLayout= (DrawerLayout) findViewById(R.id.activity_main_drawer);
        NavigationView navView= (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();//如果存在这样一个标题栏
        if (actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);//暂时先设置默认图片
        }
        navView.setCheckedItem(R.id.nav_shoucang);  //默认选项
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_shoucang:
//                        Toast.makeText(MainActivity.this,"你点击了shoucang",Toast.LENGTH_SHORT).show();
                        //跳转到收藏的界面去
                        Intent intent2=new Intent(MainActivity.this, MyFavoriteActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_back:
//                        Toast.makeText(MainActivity.this,"你点击了friend",Toast.LENGTH_SHORT).show();
                        Intent intent3=new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent3);
                        break;
//                    case R.id.nav_location:
//                        Toast.makeText(MainActivity.this,"你点击了lacation",Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.nav_sao:
//                        Toast.makeText(MainActivity.this,"你点击了扫一扫",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this, CaptureActivity.class);
                        startActivityForResult(intent,1);
                        break;
                    case R.id.nav_yao:
//                        Toast.makeText(MainActivity.this,"你点击了摇一摇",Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(MainActivity.this,YaoYiYaoActivity.class);
                        startActivity(intent1);
                        break;

                }
                mDrawerLayout.closeDrawers();

                return true;

            }
        });

        //取数据
        dbHelper=new MyDBHelper(this,"MyDB.db",null,1);
        //取数据库中的用户信息  添加到集合中
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor= db.query("UserInfo",new String[]{"email"},"nickName = ?",new String[]{nName},null,null,null);

        if (cursor.moveToFirst()) {
            //如果返回值有一个结果即true的话
            //说明有值，有这个用户
            //取这个密码
            String emailTemp = cursor.getString(0);

            TextView a = (TextView) navView.getHeaderView(0).findViewById(R.id.nickname);
            TextView b = (TextView) navView.getHeaderView(0).findViewById(R.id.mail);
            a.setText(nName);

            b.setText(emailTemp);
        }
//        while(cursor.moveToNext())
//        {
            //通过列号取值
//            int id=cursor.getInt(0);
//            String regeistName=cursor.getString(1);
//            String regeistPassword=cursor.getString(2);
//            String regeistCheckPassword=cursor.getString(3);
//            String phone=cursor.getString(4);
//            String sex=cursor.getString(5);
//            String nickName=cursor.getString(6);
//            String content=cursor.getString(7);

//        }




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if (data!=null)
                {
                    Bundle bundle=data.getExtras();
                    if (bundle==null)
                    {
                        return; //为空不处理
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS)
                    {
                        //成功
                        String result=bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(MainActivity.this,"解析结果"+result,Toast.LENGTH_SHORT).show();

                    }else{
                        //失败
                        Toast.makeText(MainActivity.this,"解析失败！",Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.maintoolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
//            case R.id.backup:
//                Toast.makeText(this,"你点击了Backup",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.delete:
//                Toast.makeText(this,"你点击了Delete",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.settings:
//                Toast.makeText(this,"你点击了Settings",Toast.LENGTH_SHORT).show();
//                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);//打开侧滑栏
                break;
            default:
        }
        return true;
    }

}
