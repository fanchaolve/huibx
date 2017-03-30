package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.MyPerOrderDetailBean;
import com.bb.hbx.bean.TradeDetailType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bb.hbx.R.id.tv_line;

/**我的--个险保单--保单详情
 * Created by Administrator on 2017/1/6.
 */

public class MyPerOrderDetailAdapter extends RecyclerView.Adapter<MyPerOrderDetailAdapter.MyViewHolder>{

    private List<TradeDetailType.InsureListBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public MyPerOrderDetailAdapter(List<TradeDetailType.InsureListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.layout_perorder_detail,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title_tv.setText(list.get(position).getInsureName());
        String money = list.get(position).getInsureAmount();
        if (!TextUtils.isEmpty(money)) {
            float money1 = Float.parseFloat(money);
            money = (int)(money1 / 10000) + "万";
        }
        holder.price_tv.setText(money);
        if (position == list.size() - 1) {
            holder.tv_line.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.price_tv)
        TextView price_tv;

        @BindView(R.id.tv_line)
        TextView tv_line;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
