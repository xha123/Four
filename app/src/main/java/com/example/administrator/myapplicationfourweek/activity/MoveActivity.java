package com.example.administrator.myapplicationfourweek.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplicationfourweek.R;
import com.example.administrator.myapplicationfourweek.tools.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/12/29.
 */
public class MoveActivity extends BaseActivity{
    Button start_bu,next_bu;
    TextView tv1,tv2,tv3,tv4,tv5;
    TextView show_tv;
    MyReciver mr;
    @Override
    public void inidata() {
        mr = new MyReciver();//注册广播接收器
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("xha");
        //执行注册
        registerReceiver(mr,intentFilter);
    }

    @Override
    public void setCon() {
        setContentView(R.layout.activity_move);
    }

    @Override
    public void iniview() {
        start_bu = (Button) findViewById(R.id.move_bu);
        tv1 = (TextView) findViewById(R.id.move_tv1);
        tv2 = (TextView) findViewById(R.id.move_tv2);
        tv3 = (TextView) findViewById(R.id.move_tv3);
        tv4 = (TextView) findViewById(R.id.move_tv4);
        tv5 = (TextView) findViewById(R.id.move_tv5);
        next_bu = (Button) findViewById(R.id.move_next_bu);
        show_tv = (TextView) findViewById(R.id.move_show_tv);
    }

    @Override
    public void iniRdata() {

    }

    @Override
    public void setview() {
        tv5.setVisibility(View.GONE);
        start_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread th = new Thread(new Myrun());
                th.start();
            }
        });
        next_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(HttpActivity.class);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mr);//反注册
    }

    //自定义的广播接收器
    class MyReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //是你响应了这个广播 要执行操作
            String Action=intent.getAction();//获得Action
            if(Action.equals("xha")){//判断Action
                Bundle bundle=intent.getExtras();
                if (bundle!=null){
                    String id=bundle.getString("id");
                    //吐司广播内容
                    Toast.makeText(context,id,Toast.LENGTH_SHORT).show();
                    show_tv.setText(id);//显示广播内容
                }

            }
        }
    }

     class Myrun implements Runnable{
         @Override
         public void run() {

             int i = 0;
             while (i<4){
                 try {
                     Thread.sleep(2000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 i++;
                if (i==1){
                    handler.sendEmptyMessage(1);
                }else if (i==2){
                    handler.sendEmptyMessage(2);
                }else if (i==3){
                    handler.sendEmptyMessage(3);
                }else if (i==4){
                    handler.sendEmptyMessage(4);
                }
             }

         }
     }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    //第一个参数上下文对象，第二个参数加载的XML动画
                    Animation animation = AnimationUtils.loadAnimation(MoveActivity.this,R.anim.tv1);
                    //通过一个控件来启动动画
                    tv1.startAnimation(animation);
                    break;
                case 2:
                    Animation animation1 = AnimationUtils.loadAnimation(MoveActivity.this,R.anim.tv2);
                    //通过一个控件来启动动画
                    tv2.startAnimation(animation1);
                    break;
                case 3:
                    Animation animation2 = AnimationUtils.loadAnimation(MoveActivity.this,R.anim.tv3);
                    //通过一个控件来启动动画
                    tv3.startAnimation(animation2);
                    break;
                case 4:
                    Animation animation3 = AnimationUtils.loadAnimation(MoveActivity.this,R.anim.tv4);
                    //通过一个控件来启动动画
                    tv4.startAnimation(animation3);
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(5);
                        }
                    };
                    timer.schedule(task,1000);
                    break;
                case 5:
                    tv4.setVisibility(View.GONE);
                    tv5.setVisibility(View.VISIBLE);
                    Animation animation4 = AnimationUtils.loadAnimation(MoveActivity.this,R.anim.tv5);
                    tv5.startAnimation(animation4);
                    break;
            }
        }
    };
}
