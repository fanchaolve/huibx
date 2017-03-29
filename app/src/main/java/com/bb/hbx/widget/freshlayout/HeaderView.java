package com.bb.hbx.widget.freshlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.R;

/**
 * Created by Administrator on 2016/12/16.
 */

public class HeaderView extends FrameLayout implements PullHeader  {

    public TextView tvPullDown;
    ImageView iv_logo;

    public HeaderView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.fresh_header, this, true);
        tvPullDown = (TextView) findViewById(R.id.tv);
        iv_logo= (ImageView) findViewById(R.id.iv_logo);
    }



    @Override
    public void onDownBefore(int scrollY) {
        tvPullDown.setText("快松手,我要刷新啦～");
    }//下拉刷新

    @Override
    public void onDownAfter(int scrollY) {
        tvPullDown.setText("快松手,我要刷新啦～");
    }//松开刷新

    @Override
    public void onRefreshScrolling(int scrollY) {
        tvPullDown.setText("快松手,我要刷新啦～");
    }//准备刷新

    @Override
    public void onRefreshDoing(int scrollY) {
        tvPullDown.setText("正在刷新数据…………");
        iv_logo.setImageResource(R.drawable.homerefresh);
        AnimationDrawable animationDrawable= (AnimationDrawable) iv_logo.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void onRefreshCompleteScrolling(int scrollY, boolean isRefreshSuccess) {
        tvPullDown.setText(isRefreshSuccess ? "刷新成功" : "刷新失败");
    }

    @Override
    public void onRefreshCancelScrolling(int scrollY) {
        tvPullDown.setText("取消刷新");
    }
}
