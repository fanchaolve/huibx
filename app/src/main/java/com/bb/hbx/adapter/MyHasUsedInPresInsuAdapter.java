package com.bb.hbx.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.bean.HasUsedPreInsuBean;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/9.
 */

public class MyHasUsedInPresInsuAdapter extends RecyclerView.Adapter<MyHasUsedInPresInsuAdapter.MyViewHolder> {

    List<HasUsedPreInsuBean.PresentProductsRspBean> list = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;
    OnItemClickListener onMyItemClickListener;
    float padding = 50;//px
    float maxWidth;

    public MyHasUsedInPresInsuAdapter(List<HasUsedPreInsuBean.PresentProductsRspBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        maxWidth = (mContext.getResources().getDisplayMetrics().widthPixels - padding * 2) / 2;
    }

    public void setOnMyItemClickListener(OnItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hasused_present_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        reSizeTextView(holder.title_tv, "无忧自驾意外保障00000", maxWidth);
        holder.more_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemClickListener.onMyItemClickListener(position);
            }
        });
        holder.title_tv.setText(list.get(position).getInsuredName());
        holder.orderNumber_tv.setText(list.get(position).getPolicyNo());
        holder.insured_tv.setText(list.get(position).getInsuredName());
        holder.holder_tv.setText(list.get(position).getPolicyholderName());
        String startTime = list.get(position).getStartTime();
        String endTime = list.get(position).getEndTime();
        holder.time_tv.setText(startTime + " 至 " + (endTime));
        GlideUtil.getInstance().loadImage(MyApplication.getAppContext(),holder.logo_iv,list.get(position).getProductLogo(),true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //固定控件宽高,文本大小根据文本个数自适应控件宽高
    private void reSizeTextView(TextView textView, String text, float maxWidth) {
        Paint paint = textView.getPaint();
        float textWidth = paint.measureText(text);
        int textSizeInDp = 30;

        if (textWidth > maxWidth) {
            for (; textSizeInDp > 0; textSizeInDp--) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSizeInDp);
                paint = textView.getPaint();
                textWidth = paint.measureText(text);
                if (textWidth <= maxWidth) {
                    break;
                }
            }
        }
        textView.invalidate();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_tv)
        TextView title_tv;                  //保险名称

        @BindView(R.id.orderNumber_tv)
        TextView orderNumber_tv;            //保单号

        @BindView(R.id.insured_tv)
        TextView insured_tv;                //被保险人

        @BindView(R.id.holder_tv)
        TextView holder_tv;                 //投保人

        @BindView(R.id.time_tv)
        TextView time_tv;                   //保险期间

        @BindView(R.id.more_tv)
        TextView more_tv;                   //为客户推荐相似产品

        @BindView(R.id.logo_iv)
        ImageView logo_iv;                  //产品logo

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
