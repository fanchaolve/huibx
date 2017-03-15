package com.bb.hbx.provide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.bean.BKItem;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.widget.multitype.ItemViewProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fancl.
 */

public class BKItemProvide extends ItemViewProvider<BKItem, BKItemProvide.ViewHolder> {

    static OnItemClickListener mItemClickListener;

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_bk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BKItem modleItem) {
        holder.setData(modleItem);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.img_icon)
        ImageView img_icon;
        @BindView(R.id.tv_more)
        TextView tv_more;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


        void setData(@NonNull final BKItem modleItem) {
            img_icon.setImageResource(modleItem.getImg_Id());
            tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyApplication.getAppContext(),"position",Toast.LENGTH_SHORT).show();
                    mItemClickListener.onMyItemClickListener(modleItem.getImg_Id());
                }
            });
        }
    }


}
