package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.CarOrderBean;
import com.bb.hbx.bean.CarOrderBean.TypeListBean.InsureListBean.ExtraInsureBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/7.
 */

public class MyExtraCarOrderDetailAdapter extends RecyclerView.Adapter<MyExtraCarOrderDetailAdapter.MyViewHolder>{

    private List<ExtraInsureBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public MyExtraCarOrderDetailAdapter(List<ExtraInsureBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.layout_carorder_extra_detail,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title_tv.setText(list.get(position).getExtraInsureName());
        String price = list.get(position).getExtraInsureAmount();
        holder.state_tv.setText(TextUtils.isEmpty(price) ? "未投保" : "投保");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.state_tv)
        TextView state_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
