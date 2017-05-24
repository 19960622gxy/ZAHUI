package com.example.yuer.zahui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.bean.LiShiDetailResponse;
import com.example.yuer.zahui.utils.Api;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class LiShiDetailActivity extends AppCompatActivity {

    private static final String TAG = "LiShiDetailActivity";
    private ActionBar actionBar;
    private String ID;

    TextView timeY,timeM,timeD;
    TextView time2;
    TextView title;
    ImageView image;
    TextView body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishi_detail);

        Toolbar toolbar=(Toolbar) findViewById(R.id.lishi_detail_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        getSupportActionBar().setTitle("那年今日知多少");

        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }

        ID=getIntent().getStringExtra("id");
//        Toast.makeText(this,"ID= "+ID,Toast.LENGTH_SHORT).show();

//
        title=(TextView) findViewById(R.id.tv_item_lishi_title_detail);
        image=(ImageView) findViewById(R.id.lishi_detail_image);
        body=(TextView) findViewById(R.id.tv_item_lishi_content__detail);
        timeY = (TextView) findViewById(R.id.tv_item_lishi_yeartime_detail);
        timeM = (TextView) findViewById(R.id.tv_item_lishi_monthtime_detail);
        timeD = (TextView) findViewById(R.id.tv_item_lishi_daytime_detail);
        time2 = (TextView) findViewById(R.id.tv_item_lishi_yinlitime_detail);

//
//        final LiShiDetailResponse.ResultBean lishis = resultList.get(position);
//        holder.timeY.setText(lishis.getYear()+"");
//        holder.timeM.setText(lishis.getMonth()+"");
//        holder.timeD.setText(lishis.getDay()+"");
//        holder.time2.setText(lishis.getLunar());
//
//        holder.title.setText(lishis.getTitle());
//        holder.body.setText(lishis.getContent());



        getData(ID);

    }

    private void getData(String ID) {


        OkGo.get(Api.HISTORY_DETAIL)
                .tag(this)
                .params("key",Api.HISTORY_APPKEY)
                .params("v","1.0")
                .params("id",ID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
                        LiShiDetailResponse lishiResponse = gson.fromJson(s,LiShiDetailResponse.class);
                        title.setText(lishiResponse.getResult().get(0).getTitle());
                        timeY.setText(lishiResponse.getResult().get(0).getYear()+"");
                        timeM.setText(lishiResponse.getResult().get(0).getMonth()+"");
                        timeD.setText(lishiResponse.getResult().get(0).getDay()+"");
                        time2.setText(lishiResponse.getResult().get(0).getLunar());
                        body.setText(lishiResponse.getResult().get(0).getContent());
                        String img=lishiResponse.getResult().get(0).getPic();
                        if (img!="")
                        {
                            image.setVisibility(View.VISIBLE);
                            Picasso.with(LiShiDetailActivity.this).load(img)
                                    .into(image);
                        }
                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.toutiaodetailtoolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share:
//                Toast.makeText(this,"你点击了Delete",Toast.LENGTH_SHORT).show();
                UMImage thumb = new UMImage(LiShiDetailActivity.this, R.mipmap.app);//资源文件
                UMWeb web = new UMWeb("http://www.baidu.com");
                web.setTitle("杂烩");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("混合的比较有营养");//描述
                //打开分享面板
                new ShareAction(LiShiDetailActivity.this)
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {
                                Toast.makeText(LiShiDetailActivity.this,"取消分享",Toast.LENGTH_SHORT).show();
                            }
                        }).open();
                break;

//            case R.id.delete:
//                Toast.makeText(this,"你点击了Delete",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.settings:
//                Toast.makeText(this,"你点击了Settings",Toast.LENGTH_SHORT).show();
//                break;
            case android.R.id.home:
                actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_pressed);//暂时先设置默认图片
                finish();//退出当前页面
                break;
            default:
        }
        return true;
    }
}
