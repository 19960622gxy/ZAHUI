package com.example.yuer.zahui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.XinWen.TouTiaoFragment;
import com.example.yuer.zahui.adapter.TouTiaoRVAdapter;
import com.example.yuer.zahui.bean.TouTiaoResponse;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class TouTiaoDetailActivity extends AppCompatActivity {
    private static final String TAG = "TouTiaoDetailActivity";
    private WebView webView;
    private ActionBar actionBar;
    private String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_tiao_detail);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toutiao_detail_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        getSupportActionBar().setTitle("新闻大爆炸");

        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }


        URL=getIntent().getStringExtra("url");

        webView = (WebView) findViewById(R.id.webview_toutiaodetail);
        //配置webview  AuthorActivity中有这部分代码
        WebSettings webSettings= webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient());  //跳转只在本应用中进行
        webView.loadUrl(URL);


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
                UMImage thumb = new UMImage(TouTiaoDetailActivity.this, R.mipmap.app);//资源文件
                UMWeb web = new UMWeb("http://www.baidu.com");
                web.setTitle("杂烩");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("混合的比较有营养");//描述
                //打开分享面板
                new ShareAction(TouTiaoDetailActivity.this)
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
                                Toast.makeText(TouTiaoDetailActivity.this,"取消分享",Toast.LENGTH_SHORT).show();
                            }
                        }).open();
                break;
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
