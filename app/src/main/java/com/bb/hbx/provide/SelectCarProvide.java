package com.bb.hbx.provide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.bean.ActivitBean;
import com.bb.hbx.utils.GlideUtil;
import com.bb.hbx.widget.RoundImageView;
import com.bb.hbx.widget.multitype.ItemViewProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/21.
 */

public class SelectCarProvide extends ItemViewProvider<CarModels, SelectCarProvide.ViewHolder> {


    private Context context;

    public SelectCarProvide(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.activit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CarModels squareBean) {
        holder.setData(squareBean);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


//        @BindView(R.id.tv_price)
//        TextView tv_price;
//

//
//
//        @BindView(R.id.tv_pro)
//        TextView tv_pro;
//
//
//
//        @BindView(R.id.tv_sales)
//        TextView tv_sales;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(@NonNull final CarModels squareBean) {

        }

    }

}