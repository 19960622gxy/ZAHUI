package com.example.yuer.zahui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class QuTuActivity extends AppCompatActivity {
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qutu);
//        Toast.makeText(this,"hahha",Toast.LENGTH_SHORT).show();


        Toolbar toolbar=(Toolbar) findViewById(R.id.qutu_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        getSupportActionBar().setTitle("趣图一览");

        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }


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
                UMImage thumb = new UMImage(QuTuActivity.this, R.mipmap.app);//资源文件
                UMWeb web = new UMWeb("http://www.baidu.com");
                web.setTitle("杂烩");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("混合的比较有营养");//描述
                //打开分享面板
                new ShareAction(QuTuActivity.this)
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
                                Toast.makeText(QuTuActivity.this,"取消分享",Toast.LENGTH_SHORT).show();
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
