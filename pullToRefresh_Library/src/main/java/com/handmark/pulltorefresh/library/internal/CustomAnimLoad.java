package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * Created by Administrator on 2017/3/22.
 */

public class CustomAnimLoad extends LoadingLayout{

    private PullToRefreshBase.Mode mode;
    private AnimationDrawable animationDrawable;

    public CustomAnimLoad(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        this.mode=mode;
        if (mode== PullToRefreshBase.Mode.PULL_FROM_START)
        {
            mHeaderImage.setImageResource(R.drawable.animhead);
            mHeaderText.setVisibility(GONE);//隐藏视图
            mSubHeaderText.setVisibility(GONE);//隐藏视图
        }
        else if (mode== PullToRefreshBase.Mode.PULL_FROM_END)
        {
            mHeaderImage.setImageResource(R.drawable.animfoot);
            mHeaderText.setVisibility(GONE);//隐藏视图
            mSubHeaderText.setVisibility(GONE);//隐藏视图
        }
        animationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();
    }

    @Override
    protected int getDefaultDrawableResId() {
        if (mode== PullToRefreshBase.Mode.PULL_FROM_START)
        {
            return R.drawable.xialashuaxin;
        }
        else
        {
            return R.drawable.indicator_bg_bottom;
        }
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    @Override
    protected void refreshingImpl() {

    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    //重新设置
    @Override
    protected void resetImpl() {
        mHeaderImage.setVisibility(VISIBLE);
        mHeaderImage.clearAnimation();
    }
}
