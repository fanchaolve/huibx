package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.GetPolicies;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.TimeUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/23.
 */

public class MyAllInCarInsuAdapter extends RecyclerView.Adapter<MyAllInCarInsuAdapter.MyViewHolder>{

    Context mContext;
    List<GetPolicies.PolicyListBean> list;
    LayoutInflater inflater;
    OnItemClickListener onMyItemClickListener;
    int payAmountInt=0;
    int classType=1;//1表示车险,2表示个险
    String sts="";
    long logJqxStartTime;
    String jqxStartTimeBuff="";
    long logJqxEndTime;
    String jqxEndTimeBuff="";

    public MyAllInCarInsuAdapter(Context mContext, List<GetPolicies.PolicyListBean> list) {
        this.mContext = mContext;
        this.list = list;
        inflater=LayoutInflater.from(mContext);
    }

    public void setOnMyItemClickListener(OnItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.all_carinsu_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        sts = list.get(position).getSts();
        if ("10".equals(sts))//保障中
        {
            holder.state_iv.setImageResource(R.drawable.baodan_baozhangzhong);
        }
        else if ("20".equals(sts))//待出单
        {
            holder.state_iv.setImageResource(R.drawable.baodan_daichudan);
        }
        else
        {
            holder.state_iv.setImageResource(R.drawable.baodan_yiguoqi);
        }
        //classType = list.get(position).getClassType();
        String tradeDate = list.get(position).getTradeDate();
        long logTime = TimeUtils.getStringToDateNoSpace(tradeDate);
        String time = TimeUtils.getDateToString(logTime);
        holder.time_tv.setText(time);
        holder.title_tv.setText(list.get(position).getProductName());
        holder.orderNumber_tv.setText("订单号: "+list.get(position).getTradeId());
        if (list.get(position).getInsuredList()!=null&&list.get(position).getInsuredList().size()>0)
        {
            holder.insured_tv.setText("被保人: "+list.get(position).getInsuredList().get(0).getInsuredName());//被保人
        }
        //holder.holder_tv.setText("投保人: "+list.get(position).getApplicant());
        String jqxStartTime = list.get(position).getJqxStartTime();
        String jqxEndTime = list.get(position).getJqxEndTime();
        if (TextUtils.isEmpty(jqxStartTime))
        {
            holder.holder_tv.setVisibility(View.GONE);
        }
        else
        {
            holder.holder_tv.setVisibility(View.VISIBLE);
//            logJqxStartTime = TimeUtils.getStringToDateNoSpace(jqxStartTime);
//            jqxStartTimeBuff = TimeUtils.getDateNoHourToString(logJqxStartTime);
//            logJqxEndTime = TimeUtils.getStringToDateNoSpace(jqxEndTime);
//            jqxEndTimeBuff = TimeUtils.getDateNoHourToString(logJqxEndTime);
            holder.holder_tv.setText("交强险起期: "+jqxStartTime+"至"+jqxEndTime);
        }
        String startTime = list.get(position).getStartTime();
        String endTime = list.get(position).getEndTime();
//        long logStartTime = TimeUtils.getStringToDateNoSpace(startTime);
//        String startTimeBuff = TimeUtils.getDateNoHourToString(logStartTime);
//        long logEndTime = TimeUtils.getStringToDateNoSpace(endTime);
//        String endTimeBuff = TimeUtils.getDateNoHourToString(logEndTime);
        holder.timeLimit_tv.setText("商业险起期: "+startTime+"至"+endTime);
        int payAmount = list.get(position).getPayAmount();
        holder.price_tv.setText((payAmount/100)+"."+(payAmount/10%10)+(payAmount%10));
        /*int commisionValue1 = list.get(position).getCommisionValue1();
        holder.income_tv.setText((commisionValue1/100)+"."+(commisionValue1/10%10)+(commisionValue1%10));*/
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

        @BindView(R.id.time_tv)
        TextView time_tv;
        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.orderNumber_tv)
        TextView orderNumber_tv;
        @BindView(R.id.insured_tv)
        TextView insured_tv;
        @BindView(R.id.holder_tv)
        TextView holder_tv;
        @BindView(R.id.timeLimit_tv)
        TextView timeLimit_tv;
        @BindView(R.id.price_tv)
        TextView price_tv;
        @BindView(R.id.income_tv)
        TextView income_tv;
        @BindView(R.id.state_iv)
        ImageView state_iv;
        @BindView(R.id.logo_iv)
        ImageView logo_iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
