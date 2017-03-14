package com.example.administrator.myapplicationfourweek.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/12/26.
 */
public class Myhttp {
    public static String getCityByGet(){
        String result = null;
        String path = "http://v.juhe.cn/weather/citys";
        StringBuffer stringBuffer =new StringBuffer(path);
        stringBuffer.append("?key=");
        stringBuffer.append("9af65401da98e288dbd1d2226567ea27");
        path = stringBuffer.toString();
        try {
            URLEncoder.encode(path,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            if (connection.getResponseCode()==200){
                //链接成功之后拿到返回的数据
                InputStream is= connection.getInputStream();//拿到返回的数据
                byte[] b=new byte[1024];
                int len=0;
                StringBuffer sb=new StringBuffer();
                while((len=is.read(b))!=-1){
                    sb.append(new String(b,0,len));
                }
                result=sb.toString();
                connection.disconnect();//关闭通道
            }else {
                result = "连接失败";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getTypeByGet(){
        String result = null;
        String path = "http://v.juhe.cn/weather/uni";
        StringBuffer stringBuffer =new StringBuffer(path);
        stringBuffer.append("?key=");
        stringBuffer.append("9af65401da98e288dbd1d2226567ea27");
        path = stringBuffer.toString();
        try {
            URLEncoder.encode(path,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            if (connection.getResponseCode()==200){
                //链接成功之后拿到返回的数据
                InputStream is= connection.getInputStream();//拿到返回的数据
                byte[] b=new byte[1024];
                int len=0;
                StringBuffer sb=new StringBuffer();
                while((len=is.read(b))!=-1){
                    sb.append(new String(b,0,len));
                }
                result=sb.toString();
                connection.disconnect();//关闭通道
            }else {
                result = "连接失败";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
