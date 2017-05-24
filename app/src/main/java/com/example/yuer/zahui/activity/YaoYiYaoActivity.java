package com.example.yuer.zahui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.bean.YaoYiYaoResponse;
import com.example.yuer.zahui.utils.Api;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class YaoYiYaoActivity extends AppCompatActivity {

//
    SensorManager sensorManager;//传感器的管理器  负责注册相关的传感器 监听对应的动作
    boolean isStart = false;
    ImageView yaoShake;
    TextView content;
    TextView time;
    Animation mAnimationS;
//
    private ActionBar actionBar;
//    private ImageButton imLiPin,imZiXun;
    private TextView tvView;
    private LinearLayout llYaoSuiji;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yao_yi_yao);

        yaoShake= (ImageView) findViewById(R.id.yao_shake);
        content= (TextView) findViewById(R.id.yao_suiji_text);
        time=(TextView) findViewById(R.id.yao_suiji_time);

        //实例化
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        //通过sensorManager注册相关的传感器
        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        //传感器监听     传感器类型（加速度传感器）     接受传感器信息的频率
//
        tvView= (TextView) findViewById(R.id.tv_view_yaoyiyao);
        llYaoSuiji= (LinearLayout) findViewById(R.id.ll_yao_suiji);


//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        llYaoSuiji.setLayoutManager(layoutManager);
//        llYaoSuiji.setAdapter(adapter);






        Toolbar toolbar=(Toolbar) findViewById(R.id.yaoyiyao_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        getSupportActionBar().setTitle("随机笑话你来摇");
        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }



    }


    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float[] values=event.values;

            float x=values[0];   //x轴方向的加速度值
            float y=values[1];//y轴方向的加速度值
            float z=values[2];//z轴方向的加速度值
//            Log.d("d", "onSensorChanged: x:"+x+"-----y:"+y+"------z:"+z);


            int medumValues=20;
                if (Math.abs(x) > medumValues || Math.abs(y) > medumValues || Math.abs(z) > medumValues) {
//                Toast.makeText(Test3Activity.this,"摇了一摇",Toast.LENGTH_SHORT).show();
                    if (!isStart) {
                        yaoyiyao();
                    }
                }




        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    //写摇一摇之后的事件
    private void yaoyiyao()
    {


        mAnimationS=AnimationUtils.loadAnimation(this,R.anim.translate_2);
        yaoShake.startAnimation(mAnimationS);

        //刚摇过  不要再显示
        isStart=true;
        getData();
        tvView.setVisibility(View.VISIBLE);
        llYaoSuiji.setVisibility(View.VISIBLE);

        Toast.makeText(YaoYiYaoActivity.this,"摇了一摇",Toast.LENGTH_SHORT).show();
        //几秒之后    2秒之后才允许再摇
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isStart=false;
            }
        },2000);



    }

    private void getData() {

        OkGo.get(Api.SUIJIXIAOHUA_HOST)
                .tag(this)
                .params("key",Api.XIAOHUA_APPKEY)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: ");
                        Gson gson = new Gson() ;
                        Random random=new Random();
                        int index=random.nextInt(1);
                        YaoYiYaoResponse xiaohuaListResponse = gson.fromJson(s,YaoYiYaoResponse.class);
                        List<YaoYiYaoResponse.ResultBean> data =xiaohuaListResponse.getResult();
                        setData(data.get(index));
                    }
                });
    }
    private void setData(final YaoYiYaoResponse.ResultBean index){

        content.setText(index.getContent());

//        //获取系统当前的时间戳
//        int time= (int) (System.currentTimeMillis() / 1000);

        //时间戳转化为Sting或Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long TIME=new Long(index.getUnixtime());
        String d = format.format(TIME);

//        time.setText(d);



//        String pubTime = index.getPubDate();//转换成小时
//
//        String time1=d.split(" ")[1];
        SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date dateNow = new Date();
        try {
            date = a.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        Calendar calendarNow = Calendar.getInstance();
        calendar.setTime(date);
        calendarNow.setTime(dateNow);
        int years = calendarNow.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        int months = calendarNow.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
       int days = calendarNow.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendarNow.get(Calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY);
       int minutes = calendarNow.get(Calendar.MINUTE) - calendar.get(Calendar.MINUTE);
        int seconds = calendarNow.get(Calendar.SECOND) - calendar.get(Calendar.SECOND);
        if (years > 0) {
            time.setText(years + "年前");
        } else {
            if (months > 0) {
                time.setText(months + "月前");
            } else {
                if (days > 0) {
                    time.setText(days + "日前");
                } else {
                    if (hours > 0) {
                        time.setText(hours + "小时前");
                    } else {
                        if (minutes > 0) {
                            time.setText(minutes + "秒前");
                        } else
                            time.setText("刚刚");
                    }
                }
            }
        }

//        title.setText(index.getTitle());


        llYaoSuiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转对应的详情页
//                Toast.makeText(YaoYiYaoActivity.this, "点击了"+index.getContent(), Toast.LENGTH_SHORT).show();
//
                Intent intent = new Intent(YaoYiYaoActivity.this,XiaoHuaDetailActivity.class);
                intent.putExtra("Content",index.getContent());  //传递给下一个activity
                startActivity(intent);
//                Intent intent=new Intent(YaoYiYaoActivity.this,XiaoHuaDetailActivity.class);
//                startActivity(intent);
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
                UMImage thumb = new UMImage(YaoYiYaoActivity.this, R.mipmap.app);//资源文件
                UMWeb web = new UMWeb("http://www.baidu.com");
                web.setTitle("杂烩");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("混合的比较有营养");//描述
                //打开分享面板
                new ShareAction(YaoYiYaoActivity.this)
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
                                Toast.makeText(YaoYiYaoActivity.this,"取消分享",Toast.LENGTH_SHORT).show();
                            }
                        }).open();
                break;

            case android.R.id.home:
                actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_pressed);//暂时先设置默认图片
                finish();//退出当前页面
                break;
            default:
        }
        return true;
    }


}
