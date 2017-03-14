package com.example.administrator.myapplicationfourweek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.myapplicationfourweek.R;
import com.example.administrator.myapplicationfourweek.tools.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/29.
 */
public class HttpActivity extends BaseActivity{
    Button bu,gb_bu;
    TextView show_tv;
    String result;
    @Override
    public void inidata() {

    }

    @Override
    public void setCon() {
        setContentView(R.layout.activity_http);
    }

    @Override
    public void iniview() {
        bu = (Button) findViewById(R.id.http_bu);
        show_tv = (TextView) findViewById(R.id.http_tv);
        gb_bu = (Button) findViewById(R.id.http_gb);
    }

    @Override
    public void iniRdata() {

    }

    @Override
    public void setview() {
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type ="top";
                set1(type);
            }
        });
        gb_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//自己发送一个广播
                Intent intent=new Intent();//声明一个intent
                Bundle bundle=new Bundle();
                bundle.putString("id","这是一个广播");
                intent.putExtras(bundle);
                intent.setAction("xha");//设置广播的Action
                sendBroadcast(intent);
            }
        });
    }

    public void set1(final String typename){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://v.juhe.cn/toutiao/index", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                result = s;
                Log.i("msg",s);
                show_tv.setText(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //访问失败
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //post数据提交给服务器
                Map<String, String> map = new HashMap<String, String>();
                map.put("type",typename);
                map.put("key","237658ec4e5e6854e4ef226aff7c7e85");
                return map;
            }
        };
        //开启一个下载队列
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //添加要下载的消息
//        requestQueue.add();
        //启动下载队列
        requestQueue.start();

    }
}
