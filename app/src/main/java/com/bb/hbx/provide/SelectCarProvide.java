package com.bb.hbx.provide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.ComCarPropsBean;
import com.bb.hbx.widget.multitype.ItemViewProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/21.
 */

public class SelectCarProvide extends ItemViewProvider<ComCarPropsBean.CarInfoListBean, SelectCarProvide.ViewHolder> {//CarModels


    private Context context;

    private int layoutPosition = -1;

    private int currentPos = 0;

    public SelectCarProvide(Context context) {
        this.context = context;
    }

    public interface OnitemClick {
        void onItemClick(ComCarPropsBean.CarInfoListBean data, int position);
    }

    private OnitemClick mOnItemClickListener;

    public void setmOnItemClickListener(OnitemClick mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.activit_select_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ComCarPropsBean.CarInfoListBean squareBean) {//CarModels squareBean
        holder.setData(squareBean);

    }

    @Override
    protected void getCurrentPositon(int position) {
        super.getCurrentPositon(position);
        currentPos = position;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_modelClass)
        TextView tv_modelClass;
        @BindView(R.id.tv_modelType)
        TextView tv_modelType;
        @BindView(R.id.tv_stalls)
        TextView tv_stalls;
        @BindView(R.id.tv_releaseYear)
        TextView tv_releaseYear;
        @BindView(R.id.tv_configName)
        TextView tv_configName;
        @BindView(R.id.tv_seats)
        TextView tv_seats;
        @BindView(R.id.tv_carPrice)
        TextView tv_carPrice;

        @BindView(R.id.iv_select)
        ImageView iv_select;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(@NonNull final ComCarPropsBean.CarInfoListBean squareBean) {
            tv_modelClass.setText(squareBean.getModelClass());
            tv_modelType.setText(squareBean.getModelType());
            tv_stalls.setText(squareBean.getStalls());
            tv_releaseYear.setText(squareBean.getReleaseYear());
            tv_configName.setText(squareBean.getConfigName());
            tv_seats.setText(squareBean.getSeats());
            tv_carPrice.setText(squareBean.getCarPrice()+"元");
            if (currentPos == layoutPosition) {
                iv_select.setVisibility(View.VISIBLE);
            } else {
                iv_select.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取当前点击的位置
                    layoutPosition = ViewHolder.this.getLayoutPosition();
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(squareBean, currentPos);
                    }
                }
            });
        }

    }

}
