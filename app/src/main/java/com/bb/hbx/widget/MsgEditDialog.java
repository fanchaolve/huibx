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

import static com.bb.hbx.R.id.camera_tv;
import static com.bb.hbx.R.id.mapstorage_tv;

/**
 * Created by fancl.
 */

public class MsgEditDialog extends BaseDialog implements
        View.OnClickListener{

    private TextView tv_flagAll;

    private TextView tv_clearAll;

    private TextView cancle_tv;

    private Context mContext;

    OnItemClickListener mItemFlagAllClickListener;
    OnItemClickListener mItemClearAllListener;
    OnItemClickListener mItemCancleClickListener;

    public void setItemFlagAllClickListener(OnItemClickListener mItemMapClickListener) {
        this.mItemFlagAllClickListener = mItemMapClickListener;
    }

    public void setItemClearAllListener(OnItemClickListener mItemCameralickListener) {
        this.mItemClearAllListener = mItemCameralickListener;
    }

    public void setmItemCancleClickListener(OnItemClickListener mItemCancleClickListener) {
        this.mItemCancleClickListener = mItemCancleClickListener;
    }

    /**
     * @param context    上下文
     *
     */
    public MsgEditDialog(Context context) {
        super(context, R.layout.layout_edit_msg);
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
        tv_flagAll= (TextView) findViewById(R.id.tv_flagAll);
        tv_clearAll= (TextView) findViewById(R.id.tv_clearAll);
        cancle_tv= (TextView) findViewById(R.id.cancle_tv);
        tv_flagAll.setOnClickListener(this);
        tv_clearAll.setOnClickListener(this);
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
            case R.id.tv_flagAll:
                //Toast.makeText(mContext,"wx",Toast.LENGTH_LONG).show();
                mItemFlagAllClickListener.onMyItemClickListener(0);
                break;
            case R.id.tv_clearAll:
                //Toast.makeText(mContext,"pyq",Toast.LENGTH_LONG).show();
                mItemClearAllListener.onMyItemClickListener(0);
                break;
            case R.id.cancle_tv:
                this.dismiss();
                break;
        }
    }
}
