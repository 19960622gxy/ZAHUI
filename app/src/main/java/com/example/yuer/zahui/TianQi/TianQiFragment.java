package com.example.yuer.zahui.TianQi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.activity.TIANQIActivity;
import com.example.yuer.zahui.bean.NowResponse;
import com.example.yuer.zahui.bean.SuggestionResponse;
import com.example.yuer.zahui.utils.Api;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Yuer on 2017/5/2.
 */

public class TianQiFragment extends Fragment {
    private int months;
    private int days;
    private int years;
    private TextView location;
    private TextView temp;
    private TextView nowMonth;
    private TextView nowDay;
    private TextView noWweather;
    ImageView nowimage;
    LinearLayout threeDays;
    private NowResponse nowResponse;

    private TextView xiche;
    private TextView fangshai;
    private TextView lvyou;
    private TextView chuanyi;
    private TextView ganmao;
    private TextView yundong;








    //
////    List<TouTiaoResponse.ResultBean.DataBean> resultList;
//    List<LiShiResponse.ResultBean> resultBeanList;
////    TouTiaoRVAdapter adapter;
//    LiShiRVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_tianqi,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        location = (TextView) view.findViewById(R.id.now_location);
        temp = (TextView) view.findViewById(R.id.now_temp);
        nowMonth = (TextView) view.findViewById(R.id.now_month);
        nowDay = (TextView) view.findViewById(R.id.now_day);
        nowimage = (ImageView) view.findViewById(R.id.now_image);
        noWweather = (TextView) view.findViewById(R.id.now_weather);
        threeDays = (LinearLayout) view.findViewById(R.id.ll_santaintianqi);

        threeDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),TIANQIActivity.class);
                intent.putExtra("location",nowResponse.getResults().get(0).getLocation().getName());
                intent.putExtra("temp",nowResponse.getResults().get(0).getNow().getTemperature());
                intent.putExtra("text",nowResponse.getResults().get(0).getNow().getText());
                intent.putExtra("CODE",nowResponse.getResults().get(0).getNow().getCode());
//                Toast.makeText(getContext(),"CODE= "+nowResponse.getResults().get(0).getNow().getCode(),Toast.LENGTH_SHORT).show();

                getContext().startActivity(intent);

            }
        });

        xiche=(TextView) view.findViewById(R.id.xiche);
        fangshai=(TextView) view.findViewById(R.id.fangshai);
        lvyou=(TextView) view.findViewById(R.id.lvyou);
        chuanyi=(TextView) view.findViewById(R.id.yifu);
        ganmao=(TextView) view.findViewById(R.id.ganmao);
        yundong=(TextView) view.findViewById(R.id.yundong);

        Calendar c = Calendar.getInstance();
        years = c.get(Calendar.YEAR);
        months = c.get(Calendar.MONTH);
        days = c.get(Calendar.DAY_OF_MONTH);

        int MONTH= months+1 ;
        nowMonth.setText(MONTH+"");
        nowDay.setText(days+"");
//        int DAY=days-1;
//        getData(years,MONTH,DAY);
        getData();
        getLifeData();
    }

    private void getLifeData() {
        OkGo.get(Api.LIFE)
                .tag(this)
                .params("key",Api.WEATHER_APPKAY)
                .params("location","淮安")
                .params("language","zh-Hans")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
                        SuggestionResponse lifeResponse = gson.fromJson(s,SuggestionResponse.class);
                        xiche.setText(lifeResponse.getResults().get(0).getSuggestion().getCar_washing().getBrief());
                        fangshai.setText(lifeResponse.getResults().get(0).getSuggestion().getUv().getBrief());
                        lvyou.setText(lifeResponse.getResults().get(0).getSuggestion().getTravel().getBrief());
                        chuanyi.setText(lifeResponse.getResults().get(0).getSuggestion().getDressing().getBrief());
                        ganmao.setText(lifeResponse.getResults().get(0).getSuggestion().getFlu().getBrief());
                        yundong.setText(lifeResponse.getResults().get(0).getSuggestion().getSport().getBrief());





                    }
                });



    }


    private void getData() {

//        今日天气实况
        OkGo.get(Api.WEATHER_NOW)
                .tag(this)
                .params("key",Api.WEATHER_APPKAY)
                .params("location","淮安")
                .params("language","zh-Hans")
                .params("unit","c")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
                        nowResponse = gson.fromJson(s,NowResponse.class);
                        location.setText(nowResponse.getResults().get(0).getLocation().getName());
                        temp.setText(nowResponse.getResults().get(0).getNow().getTemperature()+"°");
                        int code=Integer.parseInt(nowResponse.getResults().get(0).getNow().getCode());
                        if (code==0||code==1||code==2||code==3)
                        {
                            nowimage.setImageResource(R.mipmap.sunny);
                        }
                        else if(code==4||code==5||code==6||code==7||code==8)
                        {
                            nowimage.setImageResource(R.mipmap.cloudy);
                        }
                        else if(code==9)
                        {
                            nowimage.setImageResource(R.mipmap.overcast);
                        }
                        else if(code==10||code==11||code==12||code==13||code==14||code==15||code==16||code==17||code==18||code==19)
                        {
                            nowimage.setImageResource(R.mipmap.lightrain);
                        }
                        noWweather.setText(nowResponse.getResults().get(0).getNow().getText());


                    }
                });


//        //未来三天的天气
//        int years,int MONTH,int DAY
//        OkGo.get(Api.WEATHER_MEITIAN)
//                .tag(this)
//                .params("key",Api.WEATHER_APPKAY)
//                .params("location","淮安")
//                .params("language","zh-Hans")
//                .params("unit","c")
//                .params("start",years+"/"+MONTH+"/"+DAY)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d(TAG, "onSuccess: "+s);
//                    }
//                });




    }

}
