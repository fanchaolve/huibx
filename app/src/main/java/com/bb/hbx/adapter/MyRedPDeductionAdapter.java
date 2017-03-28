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
import com.bb.hbx.bean.GetUserCouponsListBean;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/27.
 */

public class MyRedPDeductionAdapter extends RecyclerView.Adapter<MyRedPDeductionAdapter.MyViewHolder>{

    Context mContext;
    List<GetUserCouponsListBean.CouponListBean> list;
    LayoutInflater inflater;
    OnItemClickListener onMyItemClickListener;

    public MyRedPDeductionAdapter(Context mContext, List<GetUserCouponsListBean.CouponListBean> list) {
        this.mContext = mContext;
        this.list = list;
        inflater=LayoutInflater.from(mContext);
    }

    public void setOnMyItemClickListener(OnItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.unused_redp_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (list != null && list.size() > 0) {                  //做判空处理，防止无数据返回时页面崩溃
            String offUpto = list.get(position).getOffUpto();

            int offUptoBuff = TextUtils.isEmpty(offUpto) ? 0 : Integer.parseInt(offUpto);
            holder.info_tv.setText("满" + ((offUptoBuff / 100) + "." + (offUptoBuff / 10 % 10) + (offUptoBuff % 10)) + "元可用");
            //getDateNoHourToString
            holder.price_tv.setText("¥" + list.get(position).getCouponValue());
            holder.title_tv.setText(list.get(position).getCouponName());
            String expTime = list.get(position).getExpTime();
            String effTime = list.get(position).getEffTime();
            long exp = Long.parseLong(expTime);
            long eff = Long.parseLong(effTime);
            holder.time_tv.setText("有效期:" + TimeUtils.getDateNoHourToString(exp) + "-" + TimeUtils.getDateNoHourToString(exp));
            holder.condtion_tv.setText(list.get(position).getCouponDesc());
            //holder.deadLine_iv.setTag(position);
            long time = exp - eff;
            if (time > 86400000)//判断剩余一天图标是否显示
            {
                holder.deadLine_iv.setVisibility(View.GONE);
            } else {
                holder.deadLine_iv.setVisibility(View.VISIBLE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMyItemClickListener.onMyItemClickListener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.divider_iv)
        ImageView divider_iv;
        @BindView(R.id.deadLine_iv)
        ImageView deadLine_iv;
        @BindView(R.id.state_iv)
        ImageView state_iv;
        @BindView(R.id.price_tv)
        TextView price_tv;
        @BindView(R.id.info_tv)
        TextView info_tv;
        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.time_tv)
        TextView time_tv;
        @BindView(R.id.condtion_tv)
        TextView condtion_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
