package com.bb.hbx.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bb.hbx.BaseDialog;
import com.bb.hbx.R;
import com.bb.hbx.interfaces.OnItemClickListener;


/**
 * Created by fancl.
 */

public class ChangeIconDailog extends BaseDialog implements
        View.OnClickListener{


    private TextView mapstorage_tv;

    private TextView camera_tv;

    private TextView cancle_tv;

    private Context mContext;

    OnItemClickListener mItemMapClickListener;
    OnItemClickListener mItemCameralickListener;
    OnItemClickListener mItemCancleClickListener;

    public void setmItemMapClickListener(OnItemClickListener mItemMapClickListener) {
        this.mItemMapClickListener = mItemMapClickListener;
    }

    public void setmItemCameralickListener(OnItemClickListener mItemCameralickListener) {
        this.mItemCameralickListener = mItemCameralickListener;
    }

    public void setmItemCancleClickListener(OnItemClickListener mItemCancleClickListener) {
        this.mItemCancleClickListener = mItemCancleClickListener;
    }

    /**
     * @param context    上下文
     *
     */
    public ChangeIconDailog(Context context) {
        super(context, R.layout.layout_change_usericon);
        mContext=context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();


    }

    private void init() {
        setAnimation(R.style.AnimationBottomDialog);
        setGravity(Gravity.BOTTOM);
        mapstorage_tv= (TextView) findViewById(R.id.mapstorage_tv);
        camera_tv= (TextView) findViewById(R.id.camera_tv);
        cancle_tv= (TextView) findViewById(R.id.cancle_tv);
        mapstorage_tv.setOnClickListener(this);
        camera_tv.setOnClickListener(this);
        cancle_tv.setOnClickListener(this);

    }

    @Override
    protected int dialogWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        return metric.widthPixels;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mapstorage_tv:
                //Toast.makeText(mContext,"wx",Toast.LENGTH_LONG).show();
                mItemMapClickListener.onMyItemClickListener(0);
                break;
            case R.id.camera_tv:
                //Toast.makeText(mContext,"pyq",Toast.LENGTH_LONG).show();
                mItemCameralickListener.onMyItemClickListener(0);
                break;
            case R.id.cancle_tv:
                this.dismiss();
                break;
        }
    }
}
