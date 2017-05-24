package com.example.yuer.zahui.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yuer.zahui.LiShi.FavoriteLiShiFragment;
import com.example.yuer.zahui.R;
import com.example.yuer.zahui.XiaoHua.FavoriteQutuFragment;
import com.example.yuer.zahui.XiaoHua.FavoriteXiaohuaFragment;
import com.example.yuer.zahui.XinWen.FavoriteXinWenFragment;
import com.example.yuer.zahui.XinWen.GuoJiFragment;
import com.example.yuer.zahui.XinWen.GuoNeiFragment;
import com.example.yuer.zahui.XinWen.SheHuiFragment;
import com.example.yuer.zahui.XinWen.TouTiaoFragment;
import com.example.yuer.zahui.adapter.FavoriteVPAdapter;
import com.example.yuer.zahui.adapter.HistoryListViewAdapter;
import com.example.yuer.zahui.adapter.MainVPAdapter;
import com.example.yuer.zahui.db_collection.History;
import com.example.yuer.zahui.utils.MyDBCollectionHelper;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MyFavoriteActivity extends AppCompatActivity {

//    ListView lvHistoryToday;
    HistoryListViewAdapter adapter;
    List<History> historyManager; //全局变量，使他能被使用
    //取活的数据源要使用MyDBHelper
    MyDBCollectionHelper dbHelper;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);

        Toolbar toolbar=(Toolbar) findViewById(R.id.myfavorite_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        getSupportActionBar().setTitle("我的收藏");
        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }


        TabLayout tabLayout= (TabLayout) findViewById(R.id.tablayout_favorite);
        ViewPager viewPager= (ViewPager) findViewById(R.id.vp_favorite);
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new FavoriteXinWenFragment());         //新闻
        fragmentList.add(new FavoriteLiShiFragment());         //历史
        fragmentList.add(new FavoriteXiaohuaFragment());         //笑话
        fragmentList.add(new FavoriteQutuFragment());         //趣图

//        lvHistoryToday=(ListView) findViewById(R.id.lvHistoryToday);
//        dbHelper=new MyDBCollectionHelper(this,"MyDBCollection.db",null,1);
//
//        historyManager = new ArrayList<>();
//        adapter=new HistoryListViewAdapter(this,historyManager);
//        lvHistoryToday.setAdapter(adapter);
        FavoriteVPAdapter adapter=new FavoriteVPAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);//让tabLayout和viewPager两个有关联




        //取活的数据（数据库中的数据）
//        initData();

    }

//    private void initData() {
//        //取所有数据
//
//        //取数据库中的用户信息  添加到集合中
//        SQLiteDatabase db=dbHelper.getWritableDatabase();
//        Cursor cursor= db.query("History",null,null,null,null,null,null);
//        while(cursor.moveToNext())
//        {
//            //通过列号取值
//            int id=cursor.getInt(0);
//            int historyYear=cursor.getInt(1);
//            String historyTitle=cursor.getString(2);
//            String historyDes=cursor.getString(3);
//            String historyId=cursor.getString(4);
//
//
//            //拿到一个对象
////   死数据源进行测试        User user=new User(1,"hulin","112523","112523","18363456833","女","狐狸","竭尽全力...");
//            History history=new History(id,historyYear,historyTitle,historyDes,historyId);
//            //将对象添加到集合中
//            historyManager.add(history);
//        }
//        adapter.notifyDataSetChanged();//刷新listview的adapter
//
//
//    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.shuucangtoolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

//            case R.id.share:
////                Toast.makeText(this,"你点击了Delete",Toast.LENGTH_SHORT).show();
//                UMImage thumb = new UMImage(TouTiaoDetailActivity.this, R.mipmap.app);//资源文件
//                UMWeb web = new UMWeb("http://www.baidu.com");
//                web.setTitle("杂烩");//标题
//                web.setThumb(thumb);  //缩略图
//                web.setDescription("混合的比较有营养");//描述
//                //打开分享面板
//                new ShareAction(TouTiaoDetailActivity.this)
//                        .withMedia(web)
//                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
//                        .setCallback(new UMShareListener() {
//                            @Override
//                            public void onStart(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onResult(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//
//                            }
//
//                            @Override
//                            public void onCancel(SHARE_MEDIA share_media) {
//                                Toast.makeText(TouTiaoDetailActivity.this,"取消分享",Toast.LENGTH_SHORT).show();
//                            }
//                        }).open();
//                break;
////            case R.id.settings:
////                Toast.makeText(this,"你点击了Settings",Toast.LENGTH_SHORT).show();
////                break;
            case android.R.id.home:
                actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_pressed);//暂时先设置默认图片
                finish();//退出当前页面
                break;
            default:
        }
        return true;
    }
}
