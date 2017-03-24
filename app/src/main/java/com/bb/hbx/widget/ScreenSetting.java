package com.bb.hbx.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bb.hbx.R;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ScreenSetting extends ImageView{

    private static final String tag = "ScreenSetting";
    private Context mContext = null;
    private Paint mPaint = null;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private int mLeft = 0;
    private int mTop = 0;
    private int mRight = 0;
    private int mBottom = 0;

    public ScreenSetting(Context context) {
        super(context);
        mContext=context;
        initPoint();
    }

    public ScreenSetting(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initPoint();
    }

    /*初始化坐标
    * */
    private void initPoint() {
        getScreenSize();
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.A1));
        mPaint.setStrokeWidth(4f);
        mPaint.setAntiAlias(true);
        mLeft = mScreenWidth * 2 / 100;
        mRight = mScreenWidth * 98 / 100;
        mTop = mScreenHeight * 3 / 100;
        mBottom = mScreenHeight * 97 / 100;
    }

    /*
    * 获取控件大小*/
    private void getScreenSize() {
        mScreenHeight=getHeight();
        mScreenWidth = getWidth();
        if (mScreenHeight>mScreenWidth)
        {
            int tmp=mScreenHeight;
            mScreenHeight=mScreenWidth;
            mScreenWidth=tmp;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus)
        {
            invalidate();
        }
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 左上
        canvas.drawLine(mLeft - 3, mTop, mLeft * 3, mTop, mPaint);
        canvas.drawLine(mLeft, mTop, mLeft, mTop * 4, mPaint);
        // 左下
        canvas.drawLine(mLeft - 3, mBottom, mLeft * 3, mBottom, mPaint);
        canvas.drawLine(mLeft, mBottom, mLeft, mBottom - mTop * 3, mPaint);
        // 右上
        canvas.drawLine(mRight + 3, mTop, mRight - mLeft * 2, mTop, mPaint);
        canvas.drawLine(mRight, mTop, mRight, mTop * 4, mPaint);
        // 右下
        canvas.drawLine(mRight + 3, mBottom, mRight - mLeft * 2, mBottom, mPaint);
        canvas.drawLine(mRight, mBottom, mRight, mBottom - mTop * 3, mPaint);
    }

    @Override
    public void invalidate() {
        initPoint();
        super.invalidate();
    }
}
