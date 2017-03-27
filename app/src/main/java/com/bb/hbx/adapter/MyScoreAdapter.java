package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.GetAccountDetailBean;
import com.bb.hbx.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyScoreAdapter extends RecyclerView.Adapter<MyScoreAdapter.MyViewHolder> {

    //ArrayList<MyScoreBean> list;
    List<GetAccountDetailBean.AccountDetailListBean> list;
    Context mContext;
    LayoutInflater inflater;

    public MyScoreAdapter(List<GetAccountDetailBean.AccountDetailListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_score, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.id_tv.setText(list.get(position).getTradeDesc());
//        String time = TimeUtils.getDateToString(list.get(position).getLogTime());
        String time = list.get(position).getLogTime();
        holder.time_tv.setText(time);
        int tradeType = list.get(position).getTradeType();
        int tradeAmount = list.get(position).getTradeAmount();
        if (tradeType == 10 || tradeType == 21 || tradeType == 31)//判断是收入还是支出
        {
//            holder.price_tv.setText("+ "+(tradeAmount/100)+"."+(tradeAmount/10%10)+(tradeAmount%10));
            holder.price_tv.setText("+ " + tradeAmount);
        } else {
//            holder.price_tv.setText("- "+(tradeAmount/100)+"."+(tradeAmount/10%10)+(tradeAmount%10));
            holder.price_tv.setText("- " + tradeAmount);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_tv)
        TextView id_tv;
        @BindView(R.id.time_tv)
        TextView time_tv;
        @BindView(R.id.price_tv)
        TextView price_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
