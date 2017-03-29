package com.bb.hbx.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.bean.IniviteFriendsBean.UserAccountDetailRecordBean;
import com.bb.hbx.utils.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：Created by Administrator on 2017/3/7 20:22
 * 邮箱：
 * 描述：邀请好友listview的适配器
 */
public class InviteFriendsListviewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<UserAccountDetailRecordBean> dataList;
    private int listNum;

    public InviteFriendsListviewAdapter(Context context,List<UserAccountDetailRecordBean> dataList) {
        mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        listNum = dataList.size();
    }

    @Override
    public int getCount() {
        return listNum;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_listview_invitefriends,parent,false);
            holder.serialNumber = (TextView)convertView.findViewById(R.id.item_tv_num);
            holder.item_civ_icon = (CircleImageView) convertView.findViewById(R.id.item_civ_icon);
            holder.item_tv_name = (TextView)convertView.findViewById(R.id.item_tv_name);
            holder.item_tv_money = (TextView)convertView.findViewById(R.id.item_tv_money);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.serialNumber.setText("#" + (position + 4));
        String iconPath = "";
        iconPath = dataList.get(position).getUserLogo();
        if (TextUtils.isEmpty(iconPath)) {
            holder.item_civ_icon.setImageResource(R.drawable.defult_icon);
        } else {
            GlideUtil glideUtil = GlideUtil.getInstance();
            glideUtil.loadImage(MyApplication.getAppContext(),holder.item_civ_icon,iconPath,true);
        }

//        holder.item_civ_icon.setImageResource(R.drawable.defult6);
        holder.item_tv_name.setText(dataList.get(position).getUserNickname());
        holder.item_tv_money.setText(dataList.get(position).getAcctSum() + "");
        return convertView;
    }

    /**
     * listview 的holder类
     */
    public final class ViewHolder {
        public TextView serialNumber;                   //序号item_tv_num
        public CircleImageView item_civ_icon;           //头像
        public TextView item_tv_name;                   //姓名
        public TextView item_tv_money;                  //钱
    }
}
