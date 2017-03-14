package com.example.administrator.myapplicationfourweek;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplicationfourweek.activity.RecyActivity;
import com.example.administrator.myapplicationfourweek.activity.ZmxyActivity;
import com.example.administrator.myapplicationfourweek.entity.Mytype;
import com.example.administrator.myapplicationfourweek.http.Myhttp;
import com.example.administrator.myapplicationfourweek.tools.BaseActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    Button wl_bu,next_tv;
    String wl;
    TextView show_tv,timer_tv;
    ArrayList<Mytype.ResultBean> arr;
    Boolean flag;
    FloatingActionButton floatingActionButton;
    Button share_bu,xy_bu;
    @Override
    public void inidata() {
        flag = false;
        ShareSDK.initSDK(this);//初始化
    }

    @Override
    public void setCon() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void iniview() {
        wl_bu = (Button) findViewById(R.id.main_wl_bu);
        show_tv = (TextView) findViewById(R.id.main_show_tv);
        timer_tv = (TextView) findViewById(R.id.main_timer_tv);
        next_tv = (Button) findViewById(R.id.main_next_tv);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.main_fab);
        share_bu = (Button) findViewById(R.id.main_share);
        xy_bu = (Button) findViewById(R.id.main_xy_bu);
    }

    @Override
    public void iniRdata() {

    }

    @Override
    public void setview() {
        wl_bu.setOnClickListener(this);
        next_tv.setOnClickListener(this);
        share_bu.setOnClickListener(this);
        xy_bu.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        final Timer timer = new Timer();//新建一个timer定时器
        TimerTask ts = new TimerTask() {//开启timertask
            @Override
            public void run() {
                if (flag){//使用flag标签作标示
                   timer.cancel();
                }else{
                    handler.sendEmptyMessage(1);//用hander发消息
                }

            }
        };
        timer.schedule(ts,1000,2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_wl_bu:
                Thread th = new Thread(new Mywl());
                th.start();
                break;
            case R.id.main_next_tv:
                intent(RecyActivity.class);
                break;
            case R.id.main_fab:
                toast(this,"点击");
                break;
            case R.id.main_share:
                toast(this,"123");
                showShare();
                break;
            case R.id.main_xy_bu:
                intent(ZmxyActivity.class);
                break;
        }
    }
    class Mywl implements Runnable{

        @Override
        public void run() {
            String st = Myhttp.getTypeByGet();
            Message ms = new Message();
            ms.what = 0;
            ms.obj = st;
            handler.sendMessage(ms);

        }
    }
    int mm = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    wl = (String) msg.obj;
                    Log.i("msg",wl.toString());
                    jiexi();
                    caozuo();
                    break;
                case  1:
                    mm++;
                    if (mm==10){
                        flag = true;
                    }
                    timer_tv.setText(mm+"");//显示
                    break;
            }
        }


    };
    private void caozuo() {
        show_tv.setText(arr.get(1).getWeather());
    }
    private void jiexi() {
        try {
            JSONObject jo = new JSONObject(wl);
            if (jo.getString("resultcode").equals("200")){
                JSONArray result = jo.getJSONArray("result");
                arr = new Gson().fromJson(result.toString(),Mytype.class);

            }else {
                Toast.makeText(this,jo.getString("reason"),Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
