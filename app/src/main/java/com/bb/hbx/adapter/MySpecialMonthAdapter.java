package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bb.hbx.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/23.
 */

public class MySpecialMonthAdapter extends RecyclerView.Adapter<MySpecialMonthAdapter.MyViewHolder>{

    ArrayList<String> list;
    Context mContext;
    LayoutInflater inflater;

    public MySpecialMonthAdapter(ArrayList<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_specialmonth, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
