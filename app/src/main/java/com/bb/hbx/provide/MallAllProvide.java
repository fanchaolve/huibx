package com.bb.hbx.provide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.activitiy.ProductDetailActivity;
import com.bb.hbx.bean.Product;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.GlideUtil;
import com.bb.hbx.widget.multitype.ItemViewProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MallAllProvide extends ItemViewProvider<Product, MallAllProvide.ViewHolder> {


    private Context context;

    public MallAllProvide(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.fragment_mall_item_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Product squareBean) {
        holder.setData(squareBean);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


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


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(@NonNull final Product squareBean) {


            tv_name.setText(squareBean.getProductName());
            tv_detail1.setVisibility(View.GONE);
            tv_detail2.setVisibility(View.GONE);
            tv_detail3.setVisibility(View.GONE);
            if (squareBean.getBenefitList()!=null&&squareBean.getBenefitList().size()>0)
            {
                tv_detail1.setVisibility(View.VISIBLE);
                tv_detail1.setText(squareBean.getBenefitList().get(0).getBenefitName()+":"+squareBean.getBenefitList().get(0).getInsuredAmount());
                if (squareBean.getBenefitList().size()>1)
                {
                    tv_detail2.setVisibility(View.VISIBLE);
                    tv_detail2.setText(squareBean.getBenefitList().get(1).getBenefitName()+":"+squareBean.getBenefitList().get(1).getInsuredAmount());
                }
                if (squareBean.getBenefitList().size()>2)
                {
                    tv_detail3.setVisibility(View.VISIBLE);
                    tv_detail3.setText(squareBean.getBenefitList().get(2).getBenefitName()+":"+squareBean.getBenefitList().get(2).getInsuredAmount());
                }

            }
            tv_insurancecompany.setText(squareBean.getInsurerName());
            tv_price.setText(context.getString(R.string.howPrice, squareBean.getMinPremium()));

            tv_sales.setText(context.getString(R.string.xlmatch, squareBean.getTotalAmount()));
            tv_pro.setText("推广费"+squareBean.getCommisionValue1()+"%");
            if (MyApplication.user.getIsBClient()) {
                tv_pro.setVisibility(View.VISIBLE);
                tv_sales.setVisibility(View.GONE);
            } else {
                tv_pro.setVisibility(View.GONE);
                tv_sales.setVisibility(View.VISIBLE);
            }

            GlideUtil.getInstance().loadImage(context, img_pic, squareBean.getProductLogo(), true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("productId", squareBean.getProductId());
                    AppManager.getInstance().showActivity(ProductDetailActivity.class, bundle);
                }
            });

        }

    }

}
