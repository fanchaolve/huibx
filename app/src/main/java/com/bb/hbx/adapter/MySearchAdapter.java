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
import com.bb.hbx.bean.Product;
import com.bb.hbx.utils.GlideUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MySearchAdapter extends RecyclerView.Adapter<MySearchAdapter.MyViewHodler>{
    ArrayList<Product> list;
    LayoutInflater inflater;
    Context mContext;

    public MySearchAdapter(ArrayList<Product> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_search_item, parent, false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        holder.tv_name.setText(list.get(position).getProductName());
        holder.tv_detail1.setVisibility(View.GONE);
        holder.tv_detail2.setVisibility(View.GONE);
        holder.tv_detail3.setVisibility(View.GONE);
        if (list.get(position).getBenefitList()!=null&&list.get(position).getBenefitList().size()>0)
        {
            holder.tv_detail1.setVisibility(View.VISIBLE);
            holder.tv_detail1.setText(list.get(position).getBenefitList().get(0).getBenefitName()+":"+list.get(position).getBenefitList().get(0).getInsuredAmount());
            if (list.get(position).getBenefitList().size()>1)
            {
                holder.tv_detail2.setVisibility(View.VISIBLE);
                holder.tv_detail2.setText(list.get(position).getBenefitList().get(1).getBenefitName()+":"+list.get(position).getBenefitList().get(1).getInsuredAmount());
            }
            if (list.get(position).getBenefitList().size()>2)
            {
                holder.tv_detail3.setVisibility(View.VISIBLE);
                holder.tv_detail3.setText(list.get(position).getBenefitList().get(2).getBenefitName()+":"+list.get(position).getBenefitList().get(2).getInsuredAmount());
            }

        }
        holder.tv_insurancecompany.setText(list.get(position).getInsurerName());
        holder.tv_price.setText(mContext.getString(R.string.howPrice, list.get(position).getMinPremium()));

        holder.tv_sales.setText(mContext.getString(R.string.xlmatch, list.get(position).getTotalAmount()));
        holder.tv_pro.setText("推广费"+list.get(position).getCommisionValue1()+"%");
        if (MyApplication.user.getIsBClient()) {
            holder.tv_pro.setVisibility(View.VISIBLE);
            holder.tv_sales.setVisibility(View.GONE);
        } else {
            holder.tv_pro.setVisibility(View.GONE);
            holder.tv_sales.setVisibility(View.VISIBLE);
        }

        GlideUtil.getInstance().loadImage(mContext, holder.img_pic, list.get(position).getProductLogo(), true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        @BindView(R.id.img_pic)
        ImageView img_pic;

        @BindView(R.id.tv_insurancecompany)
        TextView tv_insurancecompany;//保险

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_grade1)
        TextView tv_grade1;

        @BindView(R.id.tv_grade2)
        TextView tv_grade2;


        @BindView(R.id.tv_detail1)
        TextView tv_detail1;

        @BindView(R.id.tv_detail2)
        TextView tv_detail2;

        @BindView(R.id.tv_detail3)
        TextView tv_detail3;

        @BindView(R.id.tv_price)
        TextView tv_price;

        @BindView(R.id.tv_sales)
        TextView tv_sales;

        @BindView(R.id.tv_pro)
        TextView tv_pro;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
