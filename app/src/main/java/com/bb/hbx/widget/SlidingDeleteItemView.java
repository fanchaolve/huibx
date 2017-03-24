package com.bb.hbx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.bb.hbx.R;

/**
 * Created by Administrator on 2017/3/23.
 * 自定义recycleview的可以侧滑删除的item
 */

public class SlidingDeleteItemView extends HorizontalScrollView {
    private TextView mTextView_Delete;

    private int mScrollWidth;

    private IonSlidingDeleteListener mIonSlidingDeleteListener;

    private Boolean isOpen = false;
    private Boolean once = false;


    public SlidingDeleteItemView(Context context) {
        this(context, null);
    }

    public SlidingDeleteItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlidingDeleteItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(!once){
            mTextView_Delete = (TextView) findViewById(R.id.tv_delete);
            once = true;
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(0,0);
            //获取水平滚动条可以滑动的范围，即右侧按钮的宽度
            mScrollWidth = mTextView_Delete.getWidth();
            Log.i("asd", "mScrollWidth:" + mScrollWidth);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mIonSlidingDeleteListener.onDownOrMove(this);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                changeScrollx();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mTextView_Delete.setTranslationX(l - mScrollWidth);
    }

    /**
     * 按滚动条被拖动距离判断关闭或打开菜单
     */
    public void changeScrollx(){
        if(getScrollX() >= (mScrollWidth/2)){
            this.smoothScrollTo(mScrollWidth, 0);
            isOpen = true;
            mIonSlidingDeleteListener.onMenuIsOpen(this);
        }else{
            this.smoothScrollTo(0, 0);
            isOpen = false;
        }
    }

    /**
     * 打开菜单
     */
    public void openMenu()
    {
        if (isOpen){
            return;
        }
        this.smoothScrollTo(mScrollWidth, 0);
        isOpen = true;
        mIonSlidingDeleteListener.onMenuIsOpen(this);
    }

    /**
     * 关闭菜单
     */
    public void closeMenu()
    {
        if (!isOpen){
            return;
        }
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return false;
//    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return true;
//    }

    public void setSlidingDeleteListener(IonSlidingDeleteListener listener){
        mIonSlidingDeleteListener = listener;
    }

    public interface IonSlidingDeleteListener{
        void onMenuIsOpen(View view);
        void onDownOrMove(SlidingDeleteItemView slidingDeleteItemView);
    }


}
