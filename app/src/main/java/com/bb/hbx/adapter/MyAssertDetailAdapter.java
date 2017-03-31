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
 * Created by Administrator on 2017/2/22.
 */

public class MyAssertDetailAdapter extends RecyclerView.Adapter<MyAssertDetailAdapter.MyViewHolder>{

    Context mContext;
    List<GetAccountDetailBean.AccountDetailListBean> list;
    LayoutInflater inflater;

    public MyAssertDetailAdapter(Context mContext, List<GetAccountDetailBean.AccountDetailListBean> list) {
        this.mContext = mContext;
        this.list = list;
        inflater=LayoutInflater.from(mContext);
    }

    public void setList(List<GetAccountDetailBean.AccountDetailListBean> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_myassertdetail_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title_tv.setText(list.get(position).getTradeDesc());
//        holder.time_tv.setText(TimeUtils.getDateNoHourToString(list.get(position).getLogTime()));
        holder.time_tv.setText(list.get(position).getLogTime());
        int tradeType = list.get(position).getTradeType();
        int tradeAmount = list.get(position).getTradeAmount();
        if (tradeType==10||tradeType==21||tradeType==31)//判断是收入还是支出
        {
//            holder.price_tv.setText("+ "+(tradeAmount/100)+"."+(tradeAmount/10%10)+(tradeAmount%10));
            holder.price_tv.setText("+" + (float) tradeAmount / 100);
        }
        else
        {
//            holder.price_tv.setText((tradeAmount/100)+"."+(tradeAmount/10%10)+(tradeAmount%10));
            holder.price_tv.setText((float) tradeAmount / 100 + "");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.time_tv)
        TextView time_tv;
        @BindView(R.id.price_tv)
        TextView price_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
