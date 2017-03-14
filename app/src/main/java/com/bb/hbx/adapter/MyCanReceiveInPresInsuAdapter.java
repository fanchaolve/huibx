package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.bean.PresentInsuBean;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/9.
 */

public class MyCanReceiveInPresInsuAdapter extends RecyclerView.Adapter<MyCanReceiveInPresInsuAdapter.MyViewHolder>{

    List<PresentInsuBean.PresentProductsRspBean> list;
    Context mContext;
    LayoutInflater inflater;
    //null object pattern avoid null point exception
    OnItemClickListener onPresentClickListener = new OnItemClickListener() {
        @Override
        public void onMyItemClickListener(int position) {

        }
    };
    OnItemClickListener onBuyClickListener = new OnItemClickListener() {
        @Override
        public void onMyItemClickListener(int position) {

        }
    };

    public MyCanReceiveInPresInsuAdapter(List<PresentInsuBean.PresentProductsRspBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }

    public void setOnBuyClickListener(OnItemClickListener onBuyClickListener) {
        this.onBuyClickListener = onBuyClickListener;
    }

    public void setOnPresentClickListener(OnItemClickListener onPresentClickListener) {
        this.onPresentClickListener = onPresentClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.canreceive_present_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        GlideUtil.getInstance().loadImage(MyApplication.getAppContext(),holder.title_iv,list.get(position).getProductLogo(),true);
        holder.count_tv.setText(list.get(position).getBuyCount() + "");
//        holder.count_tv.setText("125630");
        holder.title_tv.setText(list.get(position).getProductName());
        GlideUtil.getInstance().loadImage(MyApplication.getAppContext(),holder.logo_iv,list.get(position).getInsurerLogo(),true);
        holder.minAge_tv.setText(list.get(position).getMinAge());
        holder.maxAge_tv.setText(list.get(position).getMaxAge());
        holder.price_tv.setText(list.get(position).getSumInsured() + "元");
        holder.time_tv.setText(list.get(position).getGuaranteePeriod() + "");
        holder.deadLine_tv.setText(list.get(position).getExpTime() + "");     //剩余时间，没有进行格式化，单位ms

        holder.present_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPresentClickListener.onMyItemClickListener(position);
            }
        });
        holder.buy_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBuyClickListener.onMyItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.count_tv)    //限领份数
        TextView count_tv;

        @BindView(R.id.title_iv)    //标题图片
        ImageView title_iv;

        @BindView(R.id.logo_iv)     //小图片
        ImageView logo_iv;

        @BindView(R.id.title_tv)    //保险名
        TextView title_tv;

        @BindView(R.id.minAge_tv)      //投保最小年龄
        TextView minAge_tv;

        @BindView(R.id.maxAge_tv)      //投保最大年龄
        TextView maxAge_tv;

        @BindView(R.id.price_tv)     //保额
        TextView price_tv;

        @BindView(R.id.time_tv)     //保障时间
        TextView time_tv;

        @BindView(R.id.deadLine_tv) //到期时间
        TextView deadLine_tv;

        @BindView(R.id.present_tv)
        TextView present_tv;

        @BindView(R.id.buy_tv)
        TextView buy_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
