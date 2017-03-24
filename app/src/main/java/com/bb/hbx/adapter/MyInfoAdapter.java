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
import com.bb.hbx.interfaces.OnItemChangeStateClickListener;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.utils.DeleteItemUtils;
import com.bb.hbx.utils.TimeUtils;
import com.bb.hbx.widget.SlidingDeleteItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bb.hbx.widget.SlidingDeleteItemView.*;
import static com.yintong.secure.c.ae.j.bi;

/**
 * Created by Administrator on 2017/2/17.
 *
 */

public class MyInfoAdapter extends RecyclerView.Adapter<MyInfoAdapter.MyViewHolder> implements IonSlidingDeleteListener {

    private Context mContext;
    private List<Message> list;
    private LayoutInflater inflater;
    private OnItemChangeStateClickListener onMyItemClickListener;
    private OnItemClickListener onDeleteItemClickListener;
    private SlidingDeleteItemView mMenu = null;
    private IonSlidingViewClickListener ionSlidingViewClickListener;

    public MyInfoAdapter(Context mContext, List<Message> list) {
        this.mContext = mContext;
        this.list = list;
        inflater=LayoutInflater.from(mContext);
        ionSlidingViewClickListener = (IonSlidingViewClickListener) mContext;
    }

    public void setOnMyItemClickListener(OnItemChangeStateClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

//    public void setOnMyItemClickListener(onitem onMyItemClickListener) {
//        this.onMyItemClickListener = onMyItemClickListener;
//    }

    public void setOnDeleteItemClickListener(OnItemClickListener onDeleteItemClickListener) {
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_slidingdelete, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        int sts = list.get(position).getSts();
        if (2==sts)
        {
            holder.circle_tv.setBackgroundResource(R.drawable.shape_circle_white);//已读
        }
        else
        {
            holder.circle_tv.setBackgroundResource(R.drawable.shape_circle_a1);//未读
        }

        //设置内容布局的宽为屏幕宽度
        holder.layout_content.getLayoutParams().width = DeleteItemUtils.getScreenWidth(mContext);

        holder.title_tv.setText(list.get(position).getMsgTitle());
        holder.content_tv.setText(list.get(position).getMsgContent());
        long stringToDateNoSpace = TimeUtils.getStringToDateNoSpace(list.get(position).getMsgTime());
        holder.time_tv.setText(TimeUtils.getDateToString(stringToDateNoSpace));
        final MyViewHolder finalHolder=holder;
        finalHolder.circle_tv.setTag(position);
        finalHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    onMyItemClickListener.onMyItemChangeStateClickListener(position,finalHolder.circle_tv);
                    int n = holder.getLayoutPosition();
                    ionSlidingViewClickListener.onItemClick(v, n);
                }
            }
        });

        //长按删除的监听
        finalHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onDeleteItemClickListener.onMyItemClickListener(position);
                return true;
            }
        });

        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                ionSlidingViewClickListener.onDeleteBtnCilck(v, n);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
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

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ((SlidingDeleteItemView) itemView).setSlidingDeleteListener(MyInfoAdapter.this);
        }
    }

    public void removeData(int position){
        list.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingDeleteItemView) view;
    }

    /**
     * 滑动或者点击了Item监听
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingDeleteItemView slidingButtonView) {
        if(menuIsOpen()){
            if(mMenu != slidingButtonView){
                closeMenu();
            }
        }
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



    public interface IonSlidingViewClickListener {
        void onItemClick(View view,int position);
        void onDeleteBtnCilck(View view,int position);
    }
}
