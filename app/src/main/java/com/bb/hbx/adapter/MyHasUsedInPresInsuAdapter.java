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

import com.bb.hbx.R;
import com.bb.hbx.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/9.
 */

public class MyHasUsedInPresInsuAdapter extends RecyclerView.Adapter<MyHasUsedInPresInsuAdapter.MyViewHolder> {

    List<String> list = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;
    OnItemClickListener onMyItemClickListener;
    float padding = 50;//px
    float maxWidth;

    public MyHasUsedInPresInsuAdapter(List<String> list, Context mContext) {
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
        holder.title_tv.setText("无忧自驾意外保障00000");
        holder.more_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemClickListener.onMyItemClickListener(position);
            }
        });
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
        TextView title_tv;

        @BindView(R.id.orderNumber_tv)
        TextView orderNumber_tv;

        @BindView(R.id.insured_tv)
        TextView insured_tv;

        @BindView(R.id.holder_tv)
        TextView holder_tv;

        @BindView(R.id.time_tv)
        TextView time_tv;

        @BindView(R.id.more_tv)
        TextView more_tv;

        @BindView(R.id.logo_iv)
        ImageView logo_iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
