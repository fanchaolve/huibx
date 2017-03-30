package com.bb.hbx.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.CarOrderBean;
import com.bb.hbx.bean.CarOrderBean.TypeListBean.InsureListBean;
import com.bb.hbx.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/7.
 */

public class MyCarOrderDetailAdapter extends RecyclerView.Adapter<MyCarOrderDetailAdapter.MyViewHolder> {

    private List<InsureListBean> list;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickListener onMyItemClickListener;
    private boolean moreOrLess = true;

    public MyCarOrderDetailAdapter(List<InsureListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setOnMyItemClickListener(OnItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_carorder_detail, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (position == (list.size() - 1)) {
            holder.price_tv.setVisibility(View.GONE);
            holder.more_layout.setVisibility(View.VISIBLE);
            holder.tv_line.setVisibility(View.GONE);
            holder.more_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moreOrLess = !moreOrLess;
                    if (moreOrLess) {
                        holder.more_iv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.xialaicon));
                    } else {//收起图标
                        holder.more_iv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.xialaicon_xiangshang));
                    }
                    onMyItemClickListener.onMyItemClickListener(position);
                }
            });
        } else {
            holder.price_tv.setVisibility(View.VISIBLE);
            holder.more_layout.setVisibility(View.GONE);
            holder.tv_line.setVisibility(View.VISIBLE);
        }
        holder.title_tv.setText(list.get(position).getInsureName());
        holder.price_tv.setText(list.get(position).getInsureAmount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.price_tv)
        TextView price_tv;
        @BindView(R.id.more_layout)
        RelativeLayout more_layout;
        @BindView(R.id.more_iv)
        ImageView more_iv;
        @BindView(R.id.tv_line)
        TextView tv_line;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
