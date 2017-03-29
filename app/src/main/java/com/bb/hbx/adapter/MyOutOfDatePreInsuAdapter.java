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
import com.bb.hbx.bean.PresentInsuBean.PresentProductsRspBean;
import com.bb.hbx.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/28.
 * 赠险产品的已过期产品界面
 */

public class MyOutOfDatePreInsuAdapter extends RecyclerView.Adapter<MyOutOfDatePreInsuAdapter.MyViewHolder> {
    private List<PresentProductsRspBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public MyOutOfDatePreInsuAdapter(Context context, List<PresentProductsRspBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.outofdate_present_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GlideUtil.getInstance().loadImage(MyApplication.getAppContext(),holder.iv_tittle,list.get(position).getProductLogo(),true);
        GlideUtil.getInstance().loadImage(MyApplication.getAppContext(),holder.iv_icon,list.get(position).getInsurerLogo(),true);
        holder.tv_title.setText(list.get(position).getProductName());
        holder.tv_minAge.setText(list.get(position).getMinAge());
        holder.tv_maxAge.setText(list.get(position).getMaxAge());
        holder.tv_price.setText(list.get(position).getSumInsured() + "元");
        holder.tv_limittime.setText(list.get(position).getGuaranteePeriod() + "");
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_tittle)    //标题图片
                ImageView iv_tittle;

        @BindView(R.id.iv_icon)     //小图片
                ImageView iv_icon;

        @BindView(R.id.tv_title)    //保险名
                TextView tv_title;

        @BindView(R.id.tv_minAge)      //投保最小年龄
                TextView tv_minAge;

        @BindView(R.id.tv_maxAge)      //投保最大年龄
                TextView tv_maxAge;

        @BindView(R.id.tv_price)     //保额
                TextView tv_price;

        @BindView(R.id.tv_limittime)     //保障时间
                TextView tv_limittime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
