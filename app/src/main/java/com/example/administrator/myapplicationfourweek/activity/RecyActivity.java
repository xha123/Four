package com.example.administrator.myapplicationfourweek.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplicationfourweek.R;
import com.example.administrator.myapplicationfourweek.adapter.MyRecyclerAdapter;
import com.example.administrator.myapplicationfourweek.tools.BaseActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/1/5.
 */
public class RecyActivity extends BaseActivity{
    RecyclerView recy;
    MyRecyclerAdapter mra;
    ArrayList<String> list;
    Button next;
    @Override
    public void inidata() {//初始化数据
        list = new ArrayList<>();
        for (int i=0;i<40;i++){
            list.add("这是数据"+i);
        }
    }

    @Override
    public void setCon() {//加载布局
        setContentView(R.layout.activity_main);
    }

    @Override
    public void iniview() {//加载控件
        recy = (RecyclerView) findViewById(R.id.main_recy);
        next = (Button) findViewById(R.id.main_next_tv);
    }

    @Override
    public void iniRdata() {

    }

    @Override
    public void setview() {
        //新建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
       //设置布局管理器
        recy.setLayoutManager(linearLayoutManager);
        //新建适配器
        mra = new MyRecyclerAdapter(this);
        //给布局加载适配器
        recy.setAdapter(mra);
        //加载数据源
        mra.setList(list);
        //提示适配器刷新
        mra.notifyDataSetChanged();
        //加item点击监听
        mra.setOncli(oncli);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyActivity.this,MoveActivity.class);
                startActivity(intent);
            }
        });

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    mra.notifyDataSetChanged();
                    break;
            }
        }
    };

    MyRecyclerAdapter.onCli oncli = new MyRecyclerAdapter.onCli() {//复写接口
        @Override
        public void onClick(int position) {//短按的操作
            ArrayList<String> arr = mra.getList();
            arr.add(position,"这是增加的数据");
            mra.setList(arr);
            mra.notifyItemInserted(position);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            };
            timer.schedule(task,100);
        }

        @Override
        public void onLongClick(int position) {//长按的操作
            ArrayList<String> arr = mra.getList();
            arr.remove(position);
            mra.setList(arr);
            mra.notifyDataSetChanged();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            };
            timer.schedule(task,100);
        }

    };
}
