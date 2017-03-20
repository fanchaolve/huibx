package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.bean.CollectionBean;
import com.bb.hbx.bean.CollectionBean.FavoritesProductsRecordBean;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bb.hbx.R.id.tv_insurancecompany;
import static com.bb.hbx.utils.ShareSPUtils.mContext;

/**
 * Created by Administrator on 2017/3/15.
 * 我的收藏页面
 */

public class MyCollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<FavoritesProductsRecordBean> list = new ArrayList<>();
    private LayoutInflater inflater;
    private OnItemClickListener onMyItemClickListener;

    public static final int CAR_INSU_VIEWTYPE = 0;           //车险布局
    public static final int PER_INSU_VIEWTYPE = 1;           //个险布局

    public MyCollectionAdapter(Context context, List<FavoritesProductsRecordBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = getViewHolderByViewType(viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FavoritesProductsRecordBean recordBean = list.get(position);
        switch (getItemViewType(position)) {
            case CAR_INSU_VIEWTYPE:
                GlideUtil.getInstance().loadImage(context,((MyCarViewHolder)holder).img_pic,recordBean.getProductLogo(),false);
                ((MyCarViewHolder)holder).tv_insurancecompany.setText(recordBean.getInsurerName());
                ((MyCarViewHolder)holder).tv_name.setText(recordBean.getProductName());
                ((MyCarViewHolder)holder).tv_detail1.setText(recordBean.getAreaDesc());
                ((MyCarViewHolder)holder).tv_detail2.setText(recordBean.getAgeDesc());

                //判断是B端还是C端，B端显示推广费，C端显示总销量
                if (MyApplication.user.getIsBClient()) {
                    ((MyCarViewHolder)holder).tv_pro.setText(recordBean.getCommisionValue1() + "%推广费");
                    ((MyCarViewHolder)holder).tv_sales.setVisibility(View.INVISIBLE);
                } else {
                    ((MyCarViewHolder)holder).tv_sales.setText(recordBean.getTotalQuantity());
                    ((MyCarViewHolder)holder).tv_pro.setVisibility(View.INVISIBLE);
                }

                break;
            case PER_INSU_VIEWTYPE:
                GlideUtil.getInstance().loadImage(context,((MyPerViewHolder)holder).img_pic,recordBean.getProductLogo(),false);
                ((MyPerViewHolder)holder).tv_insurancecompany.setText(recordBean.getInsurerName());
                ((MyPerViewHolder)holder).tv_name.setText(recordBean.getProductName());
                ((MyPerViewHolder)holder).tv_detail1.setText("投保年龄: " + recordBean.getMinAge() + "-" + recordBean.getMaxAge());
                ((MyPerViewHolder)holder).tv_detail2.setText("最低保费: " + recordBean.getMinPremium() + "万元");
                ((MyPerViewHolder)holder).tv_detail3.setText("最高保费: " + recordBean.getMaxPremium() + "万元");
                ((MyPerViewHolder)holder).tv_price.setText("￥1000元起");

                if (!TextUtils.isEmpty(recordBean.getCommisionValue1())) {
                    ((MyPerViewHolder)holder).tv_pro.setText(recordBean.getCommisionValue1() + "%推广费");
                } else {
                    ((MyPerViewHolder)holder).tv_pro.setVisibility(View.INVISIBLE);
                }

                if (!TextUtils.isEmpty(recordBean.getTotalQuantity())) {
                    ((MyPerViewHolder)holder).tv_sales.setText("销量 " + recordBean.getTotalQuantity() + "份");
                } else {
                    ((MyPerViewHolder)holder).tv_sales.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;
        if (list != null) {
            if ("1".equals(list.get(position).getClassType())) {    //classType = "1"
                viewType = CAR_INSU_VIEWTYPE;
            } else if ("2".equals(list.get(position).getClassType())) {
                viewType = PER_INSU_VIEWTYPE;                       //classType = "1"
            }
        }

        return viewType;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnMyItemClickListener(OnItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    private RecyclerView.ViewHolder getViewHolderByViewType(int viewType) {

        View carView = View.inflate(mContext, R.layout.fragment_mall_item_car, null);
        View perView = View.inflate(mContext, R.layout.fragment_mall_person_item, null);

        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case CAR_INSU_VIEWTYPE:
                holder = new MyCarViewHolder(carView);
                break;
            case PER_INSU_VIEWTYPE:
                holder = new MyPerViewHolder(perView);
                break;
        }
        return holder;
    }

    /**
     * 车险的holder
     */
    class MyCarViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_pic)
        ImageView img_pic;                      //产品图片

        @BindView(R.id.tv_insurancecompany)
        TextView tv_insurancecompany;           //保险公司名称

        @BindView(R.id.tv_name)
        TextView tv_name;                       //产品名称

        @BindView(R.id.tv_detail1)
        TextView tv_detail1;

        @BindView(R.id.tv_detail2)
        TextView tv_detail2;

        @BindView(R.id.tv_pro)                  //推广
        TextView tv_pro;

        @BindView(R.id.tv_sales)                //销量
        TextView tv_sales;

        public MyCarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    /**
     * 个险的holder
     */
    class MyPerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_pic)                         //产品图片
        ImageView img_pic;

        @BindView(R.id.tv_insurancecompany)             //保险公司名称
        TextView tv_insurancecompany;

        @BindView(R.id.tv_name)                         //产品名称
        TextView tv_name;

        @BindView(R.id.tv_grade1)                       //爆款
        TextView tv_grade1;

        @BindView(R.id.tv_grade2)                       //新品
        TextView tv_grade2;

        @BindView(R.id.tv_detail1)                      //
        TextView tv_detail1;

        @BindView(R.id.tv_detail2)
        TextView tv_detail2;

        @BindView(R.id.tv_detail3)
        TextView tv_detail3;

        @BindView(R.id.tv_price)                        //价格
        TextView tv_price;

        @BindView(R.id.tv_pro)                          //推广费
        TextView tv_pro;

        @BindView(R.id.tv_sales)                        //销量
        TextView tv_sales;

        public MyPerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
