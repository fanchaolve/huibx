package com.bb.hbx.provide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.CarOrderConfirmTableSYXBean;
import com.bb.hbx.widget.multitype.ItemViewProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CarOrderConfirmTableSYXProvider extends ItemViewProvider<CarOrderConfirmTableSYXBean, CarOrderConfirmTableSYXProvider.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.ordercarform_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CarOrderConfirmTableSYXBean carOrderConfirmTableSYXBean) {
        holder.setData(carOrderConfirmTableSYXBean);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_key)
        TextView tv_key;

        @BindView(R.id.tv_value)
        TextView tv_value;
        @BindView(R.id.tv_premium)
        TextView tv_premium;
        @BindView(R.id.tv_other)
        TextView tv_other;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(CarOrderConfirmTableSYXBean bean) {
            tv_key.setText(bean.getItemName());
            tv_value.setText(bean.getItemBaoE());
            tv_premium.setText(bean.getItemBaoF());
            tv_other.setText(bean.getItemBuJiMiam());

        }
    }
}
