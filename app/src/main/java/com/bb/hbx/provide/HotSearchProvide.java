package com.bb.hbx.provide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.HotSearchBean;
import com.bb.hbx.widget.multitype.ItemViewProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fancl
 * 热门搜索item
 */

public class HotSearchProvide extends ItemViewProvider<HotSearchBean, HotSearchProvide.ViewHolder> {

    public interface HotSearchListener {

        void selectItem(HotSearchBean bean);

    }

    private HotSearchListener listener;

    public HotSearchListener getListener() {
        return listener;
    }

    public void setListener(HotSearchListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.search_title_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull HotSearchBean hotSearchBean) {
        holder.setData(hotSearchBean);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_name)
        TextView tv_title;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(@NonNull final HotSearchBean bean) {

            tv_title.setText(bean.getKeyName());
            tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.selectItem(bean);
                }
            });
        }

    }
}
