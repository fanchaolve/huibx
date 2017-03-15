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
import com.bb.hbx.bean.ProductItem;
import com.bb.hbx.interfaces.OnItemInsureTypeClickListener;
import com.bb.hbx.utils.GlideUtil;
import com.bb.hbx.widget.multitype.ItemViewProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fancl.
 */

public class ModleItemProvide extends ItemViewProvider<ProductItem, ModleItemProvide.ViewHolder> {

    static OnItemInsureTypeClickListener mItemInsureTypeClickListener;

    public void setmItemInsureTypeClickListener(OnItemInsureTypeClickListener mItemInsureTypeClickListener) {
        this.mItemInsureTypeClickListener = mItemInsureTypeClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_modle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ProductItem modleItem) {
        holder.setData(modleItem);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


        void setData(@NonNull final ProductItem modleItem) {
            title.setText(modleItem.getInsurerTypeName());
            GlideUtil.getInstance().loadImage(MyApplication.getAppContext(),
                    img, modleItem.getInsurerTypeLogo(), true);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyApplication.getAppContext(),"position", Toast.LENGTH_SHORT).show();
                    mItemInsureTypeClickListener.onMyItemInsureTypeClickListener(modleItem.getInsurerTypeId());
                }
            });

        }
    }


}
