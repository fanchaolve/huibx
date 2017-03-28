package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.GetTradesBean;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.TimeUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/23.
 */

public class MyAllInMyOrderAdapter extends RecyclerView.Adapter<MyAllInMyOrderAdapter.MyViewHolder>{

    Context mContext;
    List<GetTradesBean.TradeListBean> list;
    LayoutInflater inflater;
    OnItemClickListener onMyItemClickListener;
    int payAmountInt=0;
    int classType=1;//1表示车险,2表示个险
    String sts="";
    long logJqxStartTime;
    String jqxStartTimeBuff="";
    long logJqxEndTime;
    String jqxEndTimeBuff="";
    public MyAllInMyOrderAdapter(Context mContext, List<GetTradesBean.TradeListBean> list) {
        this.mContext = mContext;
        this.list = list;
        inflater=LayoutInflater.from(mContext);
    }

    public void setOnMyItemClickListener(OnItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.all_myorder_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        sts = list.get(position).getSts();
        if ("10".equals(sts))
        {
            holder.state_tv.setBackgroundResource(R.drawable.item_gopay_shape);
            holder.state_tv.setText("去支付");
            holder.state_tv.setTextColor(mContext.getResources().getColor(R.color.A1));
        }
        else
        {
            //holder.state_tv.setBackgroundResource(R.drawable.item_havepay_shape);
            holder.state_tv.setText("已完成");
            holder.state_tv.setTextColor(mContext.getResources().getColor(R.color.A4));
        }
        classType = list.get(position).getClassType();
        String tradeDate = list.get(position).getTradeDate();
//        Log.i("tttttt","------------------------------------" + tradeDate);
//        long logTime = TimeUtils.getStringToDateNoSpace(tradeDate);
//        String time = TimeUtils.getDateToString(logTime);
        holder.time_tv.setText(tradeDate);
        holder.itemO_tv.setText(list.get(position).getProductName());
        holder.orderNumber_tv.setText(list.get(position).getTradeId());
        if (list.get(position).getInsuredList()!=null&&list.get(position).getInsuredList().size()>0)
        {
            holder.itemTw_tv.setText(list.get(position).getInsuredList().get(0).getInsuredName());//被保人
        }
        String startTime = list.get(position).getStartTime();
        String endTime = list.get(position).getEndTime();

//        long logStartTime = TimeUtils.getStringToDateNoSpace(startTime);
//        String startTimeBuff = TimeUtils.getDateNoHourToString(logStartTime);
//        long logEndTime = TimeUtils.getStringToDateNoSpace(endTime);
//        String endTimeBuff = TimeUtils.getDateNoHourToString(logEndTime);
        if (2==classType)//2表示个险
        {
            holder.itemTh_tv.setText(list.get(position).getPolicyHolderName());
            holder.itemF_tv.setText("保险期间: "+startTime+"至"+endTime);
        }
        else
        {
            //holder.itemTh_tv.setText(list.get(position).getPolicyHolderName());
            String jqxStartTime = list.get(position).getJqxStartTime();
            String jqxEndTime = list.get(position).getJqxEndTime();
//            Log.i("tttttt","------------------jqxStartTime------------------" + jqxStartTime);
//            Log.i("tttttt","-----------------jqxEndTime-------------------" + jqxEndTime);
            if (TextUtils.isEmpty(jqxStartTime))
            {
                holder.itemTh_tv.setVisibility(View.GONE);
            }
            else
            {
                holder.itemTh_tv.setVisibility(View.VISIBLE);
//                logJqxStartTime = TimeUtils.getStringToDateNoSpace(jqxStartTime);
//                jqxStartTimeBuff = TimeUtils.getDateNoHourToString(logJqxStartTime);
//                logJqxEndTime = TimeUtils.getStringToDateNoSpace(jqxEndTime);
//                jqxEndTimeBuff = TimeUtils.getDateNoHourToString(logJqxEndTime);
                holder.itemTh_tv.setText("交强险起期: "+jqxStartTime+"至"+jqxEndTime);
            }
            holder.itemF_tv.setText("商业险起期: "+startTime+"至"+endTime);
        }
        String payAmount = list.get(position).getPayAmount();
        if (!TextUtils.isEmpty(payAmount))
        {
            payAmountInt = Integer.parseInt(payAmount);
        }
        holder.price_tv.setText(TextUtils.isEmpty(payAmount)?"0.00":(payAmountInt/100)+"."+(payAmountInt/10%10)+(payAmountInt%10));
        int commisionValue1 = list.get(position).getCommisionValue1();
        holder.income_tv.setText((commisionValue1/100)+"."+(commisionValue1/10%10)+(commisionValue1%10));
        Glide.with(mContext).load(list.get(position).getInsurerLogo()).placeholder(R.drawable.shangcheng).into(holder.logo_iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemClickListener.onMyItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.state_tv)
        TextView state_tv;
        @BindView(R.id.time_tv)
        TextView time_tv;
        @BindView(R.id.orderNumber_tv)
        TextView orderNumber_tv;
        @BindView(R.id.itemO_tv)
        TextView itemO_tv;
        @BindView(R.id.itemTw_tv)
        TextView itemTw_tv;
        @BindView(R.id.itemTh_tv)
        TextView itemTh_tv;
        @BindView(R.id.itemF_tv)
        TextView itemF_tv;
        @BindView(R.id.price_tv)
        TextView price_tv;
        @BindView(R.id.income_tv)
        TextView income_tv;
        @BindView(R.id.logo_iv)
        ImageView logo_iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
