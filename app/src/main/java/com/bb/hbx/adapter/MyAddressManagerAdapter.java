package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.Consignees;
import com.bb.hbx.interfaces.OnItemClickListener;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/30.
 */

public class MyAddressManagerAdapter extends RecyclerView.Adapter<MyAddressManagerAdapter.MyViewHolder>{

    List<Consignees.CneeListBean> list;
    Context mContext;
    HashMap<Integer,String> map;
    LayoutInflater inflater;
    String isDefaultFlag="0";
    int prePosition;
    OnItemClickListener onMyItemClickListener;
    OnItemClickListener onMyItemDeleteClickListener;
    OnItemClickListener onMyItemEditClickListener;

    public MyAddressManagerAdapter(List<Consignees.CneeListBean> list, Context mContext, HashMap<Integer, String> map) {
        this.list = list;
        this.mContext = mContext;
        this.map = map;
        inflater=LayoutInflater.from(mContext);
    }

    public void setPrePosition(int prePosition) {
        this.prePosition = prePosition;
    }

    public void setOnMyItemClickListener(OnItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    public void setOnMyItemDeleteClickListener(OnItemClickListener onMyItemDeleteClickListener) {
        this.onMyItemDeleteClickListener = onMyItemDeleteClickListener;
    }

    public void setOnMyItemEditClickListener(OnItemClickListener onMyItemEditClickListener) {
        this.onMyItemEditClickListener = onMyItemEditClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_address, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name_tv.setText(list.get(position).getCneeName());
        String mobile = list.get(position).getMobile();
        if (!TextUtils.isEmpty(mobile)) {
            //使用正则匹配将电话号码的4-7位替换成*
            String displayPhoneNum = mobile.trim().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            holder.phone_tv.setText(displayPhoneNum);
        } else {
            holder.phone_tv.setText(mobile);
        }
        holder.address_tv.setText(list.get(position).getAddress());
        holder.select_iv.setTag(position);
        isDefaultFlag=map.get(position);
        //isDefaultFlag=list.get(position).getDefaultFlag();
        final MyViewHolder finalHolder=holder;
        finalHolder.select_iv.setSelected("1".equals(isDefaultFlag)?true:false);
        finalHolder.select_tv.setTextColor("1".equals(isDefaultFlag)?mContext.getResources().getColor(R.color.A2):mContext.getResources().getColor(R.color.A3));
        holder.select_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalHolder.select_iv.getTag()!=null&&(int)finalHolder.select_iv.getTag()==position)
                {
                    isDefaultFlag=map.get(position);
                    //isDefaultFlag=list.get(position).getDefaultFlag();
                    if ("1".equals(isDefaultFlag)?true:false)
                    {
                        /*finalHolder.select_iv.setSelected(false);
                        map.put(position,"0");
                        notifyDataSetChanged();*/
                    }
                    else
                    {
                        finalHolder.select_iv.setSelected(true);
                        map.put(prePosition,"0");
                        map.put(position,"1");
                        prePosition=position;
                        notifyDataSetChanged();
                        onMyItemClickListener.onMyItemClickListener(position);
                    }
                }
            }
        });
        holder.delete_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemDeleteClickListener.onMyItemClickListener(position);
            }
        });
        holder.edit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemEditClickListener.onMyItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.name_tv)
        TextView name_tv;
        @BindView(R.id.phone_tv)
        TextView phone_tv;
        @BindView(R.id.address_tv)
        TextView address_tv;
        @BindView(R.id.select_iv)
        ImageView select_iv;
        @BindView(R.id.select_tv)
        TextView select_tv;
        @BindView(R.id.select_layout)
        RelativeLayout select_layout;
        @BindView(R.id.address_layout)
        RelativeLayout address_layout;
        @BindView(R.id.delete_layout)
        RelativeLayout delete_layout;
        @BindView(R.id.edit_layout)
        RelativeLayout edit_layout;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
