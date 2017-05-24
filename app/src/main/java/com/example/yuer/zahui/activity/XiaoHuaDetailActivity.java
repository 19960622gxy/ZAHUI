package com.example.yuer.zahui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;


import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class XiaoHuaDetailActivity extends AppCompatActivity {



    private ActionBar actionBar;
    private String content;
    private TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaohua_detail);




        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        getSupportActionBar().setTitle("笑话详情");

        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }

        //拿控件

        detail=(TextView) findViewById(R.id.xiaohua_detail);






        //跳转到这里
        //id是int  注意对应  多打吐司提示
        content = getIntent().getStringExtra("Content");
//        Toast.makeText(XiaoHuaDetailActivity.this, "点击了"+content, Toast.LENGTH_SHORT).show();
        detail.setText(content);
//        Toast.makeText(this,"id="+id,Toast.LENGTH_SHORT).show();





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
                UMImage thumb = new UMImage(XiaoHuaDetailActivity.this, R.mipmap.app);//资源文件
                UMWeb web = new UMWeb("http://www.baidu.com");
                web.setTitle("杂烩");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("混合的比较有营养");//描述
                //打开分享面板
                new ShareAction(XiaoHuaDetailActivity.this)
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
                                Toast.makeText(XiaoHuaDetailActivity.this,"取消分享",Toast.LENGTH_SHORT).show();
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
