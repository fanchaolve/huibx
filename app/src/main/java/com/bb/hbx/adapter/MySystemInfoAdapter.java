package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.Message;
import com.bb.hbx.interfaces.OnDelBtnClickListener;
import com.bb.hbx.interfaces.OnItemChangeStateClickListener;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.DeleteItemUtils;
import com.bb.hbx.utils.RealmUtilsForMessage;
import com.bb.hbx.utils.TimeUtils;
import com.bb.hbx.widget.SlidingDeleteItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/17.
 */

public class MySystemInfoAdapter extends RecyclerView.Adapter<MySystemInfoAdapter.MyViewHodler> implements SlidingDeleteItemView.IonSlidingDeleteListener {

    private Context mContext;
    private List<Message> list;
    private LayoutInflater inflater;
    private OnItemChangeStateClickListener onMyItemClickListener;
    private OnItemClickListener onDeleteItemClickListener;
    private SlidingDeleteItemView mMenu = null;
    private OnDelBtnClickListener onDelBtnClickListener;

    public MySystemInfoAdapter(Context mContext, List<Message> list) {
        this.mContext = mContext;
        this.list = list;
        inflater=LayoutInflater.from(mContext);
    }

    public void setOnMyItemClickListener(OnItemChangeStateClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    public void setOnDeleteItemClickListener(OnItemClickListener onDeleteItemClickListener) {
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDelBtnClickListener) {
        this.onDelBtnClickListener = onDelBtnClickListener;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_slidingdelete, parent, false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHodler holder, final int position) {
        Message message = RealmUtilsForMessage.queryMessageById(list.get(position).getMsgId());
        if (message != null)
        {
            holder.circle_tv.setBackgroundResource(R.drawable.shape_circle_white);  //已读
        }
        else
        {
            holder.circle_tv.setBackgroundResource(R.drawable.shape_circle_a1);
        }

        //设置内容布局的宽为屏幕宽度
        holder.layout_content.getLayoutParams().width = DeleteItemUtils.getScreenWidth(mContext);

        holder.title_tv.setText(list.get(position).getMsgTitle());
        holder.content_tv.setText(list.get(position).getMsgContent());
        long stringToDateNoSpace = TimeUtils.getStringToDateNoSpace(list.get(position).getMsgTime());
        holder.time_tv.setText(TimeUtils.getDateToString(stringToDateNoSpace));
        final MyViewHodler finalHolder=holder;
        finalHolder.circle_tv.setTag(position);
        holder.layout_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    onMyItemClickListener.onMyItemChangeStateClickListener(position,finalHolder.circle_tv);
                }
            }
        });

        //长按删除的监听
        holder.layout_content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onDeleteItemClickListener.onMyItemClickListener(position);
                return true;
            }
        });

        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = holder.getLayoutPosition();
                onDelBtnClickListener.onDelBtnClick(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingDeleteItemView) view;
    }

    @Override
    public void onDownOrMove(SlidingDeleteItemView slidingDeleteItemView) {
        if(menuIsOpen()){
            if(mMenu != slidingDeleteItemView){
                closeMenu();
            }
        }
    }

    public void removeData(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }
    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        Log.i("asd","mMenu为null");
        return false;
    }

//    public interface OnDelBtnClickListener {
//        void onDelBtnClick(View view,int position);
//    }

    class MyViewHodler extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_delete)
        TextView btn_Delete;
        @BindView(R.id.circle_tv)
        TextView circle_tv;
        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.content_tv)
        TextView content_tv;
        @BindView(R.id.time_tv)
        TextView time_tv;
        @BindView(R.id.layout_content)
        ViewGroup layout_content;
        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ((SlidingDeleteItemView) itemView).setSlidingDeleteListener(MySystemInfoAdapter.this);
        }
    }
}
