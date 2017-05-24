package com.example.yuer.zahui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.bean.NowResponse;
import com.example.yuer.zahui.bean.WeiLaiSanTianResponse;
import com.example.yuer.zahui.utils.Api;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

public class TIANQIActivity extends AppCompatActivity {
    private static final String TAG = "TIANQIActivity";
    private ImageView btnBack;
    private TextView locationNow;
    private TextView WeatherToday;
    private TextView TempToday;
    private ImageView btnSearch;
    private String location;
    private String temp;
    private String text;
    private String CODE;

    private int months;
    private int days;
    private int years;

    LinearLayout llToday;
    LinearLayout llTomo;
    LinearLayout llNext;

    LinearLayout llNomal;
    LinearLayout llPressed;
    private TextView templow;
    private TextView temphigh;

    private TextView hanziFeng;//汉字那个风字


    private TextView windDirection;
    private TextView windScale;

    private TextView todayMonth;
    private TextView todayDay;
    private ImageView todayImage;
    private TextView todayWeather;
    private  TextView todayHigh;
    private  TextView todayLow;

    private TextView tomorrowMonth;
    private TextView tomorrowDay;
    private ImageView tomorrowImage;
    private TextView tomorrowWeather;
    private  TextView tomorrowHigh;
    private  TextView tomorrowLow;


    private TextView nextMonth;
    private TextView nextDay;
    private ImageView nextImage;
    private TextView nextWeather;
    private  TextView nextLow;
    private  TextView nextHigh;
    private WeiLaiSanTianResponse nextResponse;
    private int CoDe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianqi);


        btnBack=(ImageView) findViewById(R.id.btn_back);
        locationNow=(TextView) findViewById(R.id.location_now);
        WeatherToday=(TextView) findViewById(R.id.weather_today);
        TempToday=(TextView) findViewById(R.id.temp_today);
        btnSearch=(ImageView) findViewById(R.id.btn_search);

        todayMonth=(TextView) findViewById(R.id.today_month);
        todayDay=(TextView) findViewById(R.id.today_day);
        todayImage=(ImageView) findViewById(R.id.today_image);
        todayWeather=(TextView) findViewById(R.id.today_weather);
        todayLow=(TextView) findViewById(R.id.today_low);
        todayHigh=(TextView) findViewById(R.id.today_high);

        tomorrowMonth=(TextView) findViewById(R.id.tomorrow_month);
        tomorrowDay=(TextView) findViewById(R.id.tomorrow_day);
        tomorrowImage=(ImageView) findViewById(R.id.tomorrow_image);
        tomorrowWeather=(TextView) findViewById(R.id.tomorrow_weather);
        tomorrowLow=(TextView) findViewById(R.id.tomorrow_low);
        tomorrowHigh=(TextView) findViewById(R.id.tomorrow_high);


        nextMonth=(TextView) findViewById(R.id.next_month);
        nextDay=(TextView) findViewById(R.id.next_day);
        nextImage=(ImageView) findViewById(R.id.next_image);
        nextWeather=(TextView) findViewById(R.id.next_weather);
        nextLow=(TextView) findViewById(R.id.next_low);
        nextHigh=(TextView) findViewById(R.id.next_high);

        windDirection=(TextView) findViewById(R.id.wind_direction);
        windScale=(TextView) findViewById(R.id.wind_scale);

        llToday=(LinearLayout) findViewById(R.id.ll_today);
        llTomo=(LinearLayout) findViewById(R.id.ll_tomorrow);
        llNext=(LinearLayout) findViewById(R.id.ll_next);
        llNomal=(LinearLayout) findViewById(R.id.ll_nomal);
        llPressed=(LinearLayout) findViewById(R.id.ll_pressed);

        templow=(TextView) findViewById(R.id.templow);
        temphigh=(TextView) findViewById(R.id.temphigh);

        hanziFeng=(TextView) findViewById(R.id.hanzi_feng);






        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBack.setImageResource(R.mipmap.btn_back_pressed);
                finish();
            }
        });

        location=getIntent().getStringExtra("location");
        temp=getIntent().getStringExtra("temp");
        text=getIntent().getStringExtra("text");
        CODE=getIntent().getStringExtra("CODE");

        CoDe= Integer.parseInt(CODE);
//        Toast.makeText(this,"CODE= "+CODE,Toast.LENGTH_SHORT).show();

        locationNow.setText(location);
        WeatherToday.setText(text);
        TempToday.setText(temp);




        Calendar c = Calendar.getInstance();
        years = c.get(Calendar.YEAR);
        months = c.get(Calendar.MONTH);
        days = c.get(Calendar.DAY_OF_MONTH);


        int MONTH= months+1 ;

//
//        todayMonth.setText(MONTH+"");
//        todayDay.setText(days+"");

//        todayMonth.setText(MONTH+"");
//        todayDay.setText(days+"");
//        tomorrowMonth.setText(MONTH);
//        tomorrowDay.setText(days+1);
//
//        nextMonth.setText(MONTH);
//        nextDay.setText(days+2);

        getData(years,MONTH,days);
    }

    private void getData(int years, int MONTH, int days) {
//        未来三天的天气
//        int years,int MONTH,int DAY
        OkGo.get(Api.WEATHER_MEITIAN)
                .tag(this)
                .params("key",Api.WEATHER_APPKAY)
                .params("location","淮安")
                .params("language","zh-Hans")
                .params("unit","c")
                .params("start",years+"/"+MONTH+"/"+days)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
                        nextResponse = gson.fromJson(s,WeiLaiSanTianResponse.class);
//                        tomorrowMonth.setText(nextResponse.getResults().get(0).getDaily().get(1).);
                        todayLow.setText(nextResponse.getResults().get(0).getDaily().get(0).getLow()+"°");
                        todayHigh.setText(nextResponse.getResults().get(0).getDaily().get(0).getHigh()+"°");
                        windDirection.setText(nextResponse.getResults().get(0).getDaily().get(0).getWind_direction());
                        windScale.setText(nextResponse.getResults().get(0).getDaily().get(0).getWind_scale());




                        tomorrowLow.setText(nextResponse.getResults().get(0).getDaily().get(1).getLow()+"°");
                        tomorrowHigh.setText(nextResponse.getResults().get(0).getDaily().get(1).getHigh()+"°");

                        nextLow.setText(nextResponse.getResults().get(0).getDaily().get(2).getLow()+"°");
                        nextHigh.setText(nextResponse.getResults().get(0).getDaily().get(2).getHigh()+"°");

//                        int codetoday=Integer.parseInt(nextResponse.getResults().get(0).getDaily().get(0).getCode_day());
                        if (CoDe==0||CoDe==1||CoDe==2||CoDe==3)
                        {
                            todayImage.setImageResource(R.mipmap.sunny);

                        }
                        else if(CoDe==4||CoDe==5||CoDe==6||CoDe==7||CoDe==8)
                        {
                            todayImage.setImageResource(R.mipmap.cloudy);
                        }
                        else if(CoDe==9)
                        {
                            todayImage.setImageResource(R.mipmap.overcast);
                        }
                        else if(CoDe==10||CoDe==11||CoDe==12||CoDe==13||CoDe==14||CoDe==15||CoDe==16||CoDe==17||CoDe==18||CoDe==19)
                        {
                            todayImage.setImageResource(R.mipmap.lightrain);
                        }
                        todayWeather.setText(text);



                        int code=Integer.parseInt(nextResponse.getResults().get(0).getDaily().get(1).getCode_day());
                        if (code==0||code==1||code==2||code==3)
                        {
                            tomorrowImage.setImageResource(R.mipmap.sunny);

                        }
                        else if(code==4||code==5||code==6||code==7||code==8)
                        {
                            tomorrowImage.setImageResource(R.mipmap.cloudy);
                        }
                        else if(code==9)
                        {
                            tomorrowImage.setImageResource(R.mipmap.overcast);
                        }
                        else if(code==10||code==11||code==12||code==13||code==14||code==15||code==16||code==17||code==18||code==19)
                        {
                            tomorrowImage.setImageResource(R.mipmap.lightrain);
                        }
                        tomorrowWeather.setText(nextResponse.getResults().get(0).getDaily().get(1).getText_day());



                        int codeNext=Integer.parseInt(nextResponse.getResults().get(0).getDaily().get(2).getCode_day());
                        if (codeNext==0||codeNext==1||codeNext==2||codeNext==3)
                        {
                            nextImage.setImageResource(R.mipmap.sunny);
                        }
                        else if(codeNext==4||codeNext==5||codeNext==6||codeNext==7||codeNext==8)
                        {
                            nextImage.setImageResource(R.mipmap.cloudy);
                        }
                        else if(code==9)
                        {
                            nextImage.setImageResource(R.mipmap.overcast);
                        }
                        else if(codeNext==10||codeNext==11||codeNext==12||codeNext==13||codeNext==14||codeNext==15||codeNext==16||codeNext==17||codeNext==18||codeNext==19)
                        {
                            nextImage.setImageResource(R.mipmap.lightrain);
                        }
                        nextWeather.setText(nextResponse.getResults().get(0).getDaily().get(2).getText_day());

                        String todayTime = nextResponse.getResults().get(0).getDaily().get(0).getDate();
                        String tomoTime = nextResponse.getResults().get(0).getDaily().get(1).getDate();
                        String nextTime = nextResponse.getResults().get(0).getDaily().get(2).getDate();

                        SimpleDateFormat a=new SimpleDateFormat("yyyy-MM-dd");

                        Date date0=new Date();
                        Date date1=new Date();
                        Date date2=new Date();
//
                        Date dateNow0=new Date();
                        Date dateNow1=new Date();
                        Date dateNow2=new Date();
                        try {
                            date0=a.parse(todayTime);
                            date1=a.parse(tomoTime);
                            date2=a.parse(nextTime);
                        } catch (ParseException e) {
                           e.printStackTrace();
                        }
//
                        Calendar calendar=Calendar.getInstance();

                        Calendar calendarNow0=Calendar.getInstance();
                        calendar.setTime(date0);
                        calendarNow0.setTime(dateNow0);
                        int momth0=calendarNow0.get(Calendar.MONTH);
                        int days0=calendarNow0.get(Calendar.DAY_OF_MONTH);
                        todayMonth.setText(momth0+1+"");
                        todayDay.setText(days0+"");

                        Calendar calendarNow1=Calendar.getInstance();
                        calendar.setTime(date1);
                        calendarNow1.setTime(dateNow1);
                        int momth1=calendarNow1.get(Calendar.MONTH);
                        int days1=calendarNow1.get(Calendar.DAY_OF_MONTH);
                        tomorrowMonth.setText(momth1+1+"");
                        tomorrowDay.setText(days1+1+"");

                        Calendar calendarNow2=Calendar.getInstance();
                        calendar.setTime(date2);
                        calendarNow2.setTime(dateNow2);
                        int months2=calendarNow2.get(Calendar.MONTH);
                        int days2=calendarNow2.get(Calendar.DAY_OF_MONTH);
                        nextMonth.setText(months2+1+"");
                        nextDay.setText(days2+2+"");

                        llToday.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                llPressed.setVisibility(View.GONE);
                                llNomal.setVisibility(View.VISIBLE);
                                WeatherToday.setText(text);
                                TempToday.setText(temp);
                                windDirection.setText(nextResponse.getResults().get(0).getDaily().get(0).getWind_direction());
                                windScale.setText(nextResponse.getResults().get(0).getDaily().get(0).getWind_scale());

                            }
                        });

                        llTomo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                WeatherToday.setText(nextResponse.getResults().get(0).getDaily().get(1).getText_day());

                                llNomal.setVisibility(View.GONE);
                                llPressed.setVisibility(View.VISIBLE);
                                templow.setText(nextResponse.getResults().get(0).getDaily().get(1).getLow()+"");
                                temphigh.setText(nextResponse.getResults().get(0).getDaily().get(1).getHigh()+"");

                                windDirection.setText(nextResponse.getResults().get(0).getDaily().get(1).getWind_direction());
                                windScale.setText(nextResponse.getResults().get(0).getDaily().get(1).getWind_scale());

                            }
                        });
                        llNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                WeatherToday.setText(nextResponse.getResults().get(0).getDaily().get(2).getText_day());

                                llNomal.setVisibility(View.GONE);
                                llPressed.setVisibility(View.VISIBLE);
                                templow.setText(nextResponse.getResults().get(0).getDaily().get(2).getLow()+"");
                                temphigh.setText(nextResponse.getResults().get(0).getDaily().get(2).getHigh()+"");

                                windDirection.setText(nextResponse.getResults().get(0).getDaily().get(2).getWind_direction());
                                windScale.setText(nextResponse.getResults().get(0).getDaily().get(2).getWind_scale());

//                                if (nextResponse.getResults().get(0).getDaily().get(2).getWind_direction()
//                                        .equals("无持续风向"!=null))
//                                {
//                                    hanziFeng.setVisibility(View.GONE);
//                                }

                            }
                        });







                    }
                });
    }


}
