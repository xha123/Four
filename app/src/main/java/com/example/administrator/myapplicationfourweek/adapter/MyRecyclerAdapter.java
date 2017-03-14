package com.example.administrator.myapplicationfourweek.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplicationfourweek.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/27.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.myViewHolder>{
    Context context;
    ArrayList<String> list;

    public MyRecyclerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,null);
            //布局给缓冲类
            myViewHolder myvh = new myViewHolder(view);
            return myvh;
    }

    //数据操作
    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        if (oncli!=null){//判断回调
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oncli.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    oncli.onLongClick(position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //自定义类继承缓冲类
    class myViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
       public myViewHolder(View itemView) {
           super(itemView);
           tv = (TextView) itemView.findViewById(R.id.item_tv);
       }
   }
    //回调接口
    onCli oncli;

    public onCli getOncli() {
        return oncli;
    }

    public void setOncli(onCli oncli) {
        this.oncli = oncli;
    }

    public interface onCli{
        void onClick(int position);
        void onLongClick(int position);
    }


}
