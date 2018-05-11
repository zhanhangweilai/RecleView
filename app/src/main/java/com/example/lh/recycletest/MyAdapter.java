package com.example.lh.recycletest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lihang on 17-6-19.
 */

public class MyAdapter extends  RecyclerView.Adapter  {

    private Context context;
    private ArrayList <String> arrayList;
    private View headView;
    private boolean hasHeadView;

    public MyAdapter (Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList =  arrayList;
    }
    public void setHeaderView(View view) {
        headView = view;
        if (headView != null) {
            hasHeadView = true;
        } else {
            hasHeadView = false;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item,parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).tv.setText(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        if(hasHeadView) {
            return arrayList.size() + 1;
        }

        return arrayList.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv;
    View headView;

    public MyViewHolder(View view) {
        super(view);
        tv=(TextView) view.findViewById(R.id. tv);
    }

}