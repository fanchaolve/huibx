package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.utils.ShadowTransformer;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.List;


public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private ShadowTransformer transformer;

    private List<CardView> mViews;
    public Context mContext;

    public List<Item> mData;
    private float mBaseElevation;

    private ViewPager pager;

    public ViewPager getPager() {
        return pager;
    }

    public void setPager(ViewPager pager) {
        this.pager = pager;
    }

    public CardPagerAdapter(Context context, List<Item> list) {
        mContext = context;
        mViews = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            mViews.add(null);
        }
        this.mData = list;
    }

    public ShadowTransformer getTransformer() {
        return transformer;
    }

    public void setTransformer(ShadowTransformer transformer) {
        this.transformer = transformer;
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position % mData.size());
    }

    @Override
    public int getRealCount() {
        return mData.size();
        //return 10000;
    }

    @Override
    public int getCount() {
        return mData.size();
        //return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_insure_add, container, false);
        container.addView(view);

        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        TextView tv_changePlan= (TextView) view.findViewById(R.id.tv_changePlan);
        tv_changePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemChangePlanClickListener != null) mOnItemChangePlanClickListener.onClick(position);
            }
        });


        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) mOnItemClickListener.onClick(position);
            }
        });
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);

        //final Item item=mData.get(position);
//        mIcon.setImageResource(item.getImg());
//        mName.setText(item.getName());
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    OnItemClickListener mOnItemClickListener;
    OnItemClickListener mOnItemChangePlanClickListener;

    public void setmOnItemChangePlanClickListener(OnItemClickListener mOnItemChangePlanClickListener) {
        this.mOnItemChangePlanClickListener = mOnItemChangePlanClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
