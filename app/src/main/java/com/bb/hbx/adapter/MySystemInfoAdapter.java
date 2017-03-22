package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.Message;
import com.bb.hbx.interfaces.OnItemChangeStateClickListener;
import com.bb.hbx.utils.RealmUtilsForMessage;
import com.bb.hbx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/17.
 */

public class MySystemInfoAdapter extends RecyclerView.Adapter<MySystemInfoAdapter.MyViewHodler>{

    Context mContext;
    List<Message> list;
//    MyDBManagerSystemInfo myDBManagerSystemInfo;
    LayoutInflater inflater;
    OnItemChangeStateClickListener onMyItemClickListener;
    ArrayList<Message> dbList;

    public MySystemInfoAdapter(Context mContext, List<Message> list) {
        this.mContext = mContext;
        this.list = list;
//        this.myDBManagerSystemInfo=myDBManagerSystemInfo;
        inflater=LayoutInflater.from(mContext);
    }

    public void setOnMyItemClickListener(OnItemChangeStateClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_systeminfo, parent, false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, final int position) {
//        dbList = myDBManagerSystemInfo.queryOne(list.get(position).getMsgId());
        Message message = RealmUtilsForMessage.queryMessageById(list.get(position).getMsgId());
        if (message != null)
        {
            holder.circle_tv.setBackgroundResource(R.drawable.shape_circle_white);  //已读
        }
        else
        {
            holder.circle_tv.setBackgroundResource(R.drawable.shape_circle_a1);
        }
//        int sts = list.get(position).getSts();
//        if (2==sts)
//        {
//            holder.circle_tv.setBackgroundResource(R.drawable.shape_circle_white);
//        }
//        else
//        {
//            holder.circle_tv.setBackgroundResource(R.drawable.shape_circle_a1);
//        }
        holder.title_tv.setText(list.get(position).getMsgTitle());
        holder.content_tv.setText(list.get(position).getMsgContent());
        long stringToDateNoSpace = TimeUtils.getStringToDateNoSpace(list.get(position).getMsgTime());
        holder.time_tv.setText(TimeUtils.getDateToString(stringToDateNoSpace));
        final MyViewHodler finalHolder=holder;
        finalHolder.circle_tv.setTag(position);
        finalHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemClickListener.onMyItemChangeStateClickListener(position,finalHolder.circle_tv);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        @BindView(R.id.circle_tv)
        TextView circle_tv;
        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.content_tv)
        TextView content_tv;
        @BindView(R.id.time_tv)
        TextView time_tv;
        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
