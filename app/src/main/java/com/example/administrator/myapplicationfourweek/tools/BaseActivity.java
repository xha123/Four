package com.example.administrator.myapplicationfourweek.tools;

/**
 * Created by Administrator on 2016/12/26.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/28.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inidata();
        setCon();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iniview();
        iniRdata();
        setview();
    }

    public abstract void inidata();
    public abstract void setCon();
    public abstract void iniview();
    public abstract void iniRdata();
    public abstract void setview();


    /**
     * 吐司通知
     * @param context 上下文
     * @param showone 显示信息
     */
    public void toast(Context context,String showone){
        Toast.makeText(context,showone,Toast.LENGTH_SHORT).show();
    }


    /**
     * 带参跳转
     * @param aClass 目标activity
     * @param bundle bundle

     */
    public void intent(Class aClass,Bundle bundle){
        Intent intent = new Intent(this,aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转
     * @param aClass 目标activity
     */
    public void intent(Class aClass){
        Intent intent = new Intent(this,aClass);
        startActivity(intent);
    }

}
