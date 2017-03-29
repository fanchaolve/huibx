package com.bb.hbx.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.bean.ComCarPropsBean;
import com.bb.hbx.utils.ShadowTransformer;

import java.util.ArrayList;
import java.util.List;


public class CardPagerAdapter extends PagerAdapter implements CardAdapter {


    private ShadowTransformer transformer;

    private List<CardView> mViews;
    public Context mContext;

    public List<ComCarPropsBean.PlanListBean> mData;
    private float mBaseElevation;

    private ViewPager pager;
    String totalPrice;
    List<TextView> textViewList=new ArrayList<>();

    public ViewPager getPager() {
        return pager;
    }

    public void setPager(ViewPager pager) {
        this.pager = pager;
    }

    public List<TextView> getTextViewList() {
        return textViewList;
    }

    public void setTextViewList(List<TextView> textViewList) {
        this.textViewList = textViewList;
    }


    public CardPagerAdapter(Context context, List<ComCarPropsBean.PlanListBean> list) {
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

        TextView tv_price= (TextView) view.findViewById(R.id.tv_price);
        textViewList.add(tv_price);
        LinearLayout ll_item1 = (LinearLayout) view.findViewById(R.id.ll_item1);
        TextView tv_itemName1 = (TextView) view.findViewById(R.id.tv_itemName1);
        TextView tv_chooseAmount1 = (TextView) view.findViewById(R.id.tv_chooseAmount1);
        TextView tv_franchiseFlag1 = (TextView) view.findViewById(R.id.tv_franchiseFlag1);
        View v_xx1 = view.findViewById(R.id.v_xx1);

        LinearLayout ll_item2 = (LinearLayout) view.findViewById(R.id.ll_item2);
        TextView tv_itemName2 = (TextView) view.findViewById(R.id.tv_itemName2);
        TextView tv_chooseAmount2 = (TextView) view.findViewById(R.id.tv_chooseAmount2);
        TextView tv_franchiseFlag2 = (TextView) view.findViewById(R.id.tv_franchiseFlag2);
        View v_xx2 = view.findViewById(R.id.v_xx2);

        LinearLayout ll_item3 = (LinearLayout) view.findViewById(R.id.ll_item3);
        TextView tv_itemName3 = (TextView) view.findViewById(R.id.tv_itemName3);
        TextView tv_chooseAmount3 = (TextView) view.findViewById(R.id.tv_chooseAmount3);
        TextView tv_franchiseFlag3 = (TextView) view.findViewById(R.id.tv_franchiseFlag3);
        View v_xx3 = view.findViewById(R.id.v_xx3);

        LinearLayout ll_item4 = (LinearLayout) view.findViewById(R.id.ll_item4);
        TextView tv_itemName4 = (TextView) view.findViewById(R.id.tv_itemName4);
        TextView tv_chooseAmount4 = (TextView) view.findViewById(R.id.tv_chooseAmount4);
        TextView tv_franchiseFlag4 = (TextView) view.findViewById(R.id.tv_franchiseFlag4);
        View v_xx4 = view.findViewById(R.id.v_xx4);

        LinearLayout ll_item5 = (LinearLayout) view.findViewById(R.id.ll_item5);
        TextView tv_itemName5 = (TextView) view.findViewById(R.id.tv_itemName5);
        TextView tv_chooseAmount5 = (TextView) view.findViewById(R.id.tv_chooseAmount5);
        TextView tv_franchiseFlag5 = (TextView) view.findViewById(R.id.tv_franchiseFlag5);
        View v_xx5 = view.findViewById(R.id.v_xx5);
        ll_item1.setVisibility(View.GONE);
        ll_item2.setVisibility(View.GONE);
        ll_item3.setVisibility(View.GONE);
        ll_item4.setVisibility(View.GONE);
        ll_item5.setVisibility(View.GONE);
        v_xx1.setVisibility(View.GONE);
        v_xx2.setVisibility(View.GONE);
        v_xx3.setVisibility(View.GONE);
        v_xx4.setVisibility(View.GONE);
        v_xx5.setVisibility(View.GONE);
        if (mData.get(position).getSyxList().size()>0)
        {
            ll_item1.setVisibility(View.VISIBLE);
            v_xx1.setVisibility(View.VISIBLE);
            tv_itemName1.setText(mData.get(position).getSyxList().get(0).getItemName());
            tv_chooseAmount1.setText(mData.get(position).getSyxList().get(0).getChooseAmount());
            if ("1".equals(mData.get(position).getSyxList().get(0).getChooseAmount())||"0".equals(mData.get(position).getSyxList().get(0).getChooseAmount()))
            {
                tv_chooseAmount1.setVisibility(View.GONE);
            }
            else
            {
                tv_chooseAmount1.setVisibility(View.VISIBLE);
            }
            tv_franchiseFlag1.setText("不计免赔");
            if ("1".equals(mData.get(position).getSyxList().get(0).getFranchiseFlag()))
            {
                tv_franchiseFlag1.setVisibility(View.VISIBLE);
            }
            else
            {
                tv_franchiseFlag1.setVisibility(View.GONE);
            }
        }
        if (mData.get(position).getSyxList().size()>1)
        {
            ll_item2.setVisibility(View.VISIBLE);
            v_xx2.setVisibility(View.VISIBLE);
            tv_itemName2.setText(mData.get(position).getSyxList().get(1).getItemName());
            tv_chooseAmount2.setText(mData.get(position).getSyxList().get(1).getChooseAmount());
            if ("1".equals(mData.get(position).getSyxList().get(1).getChooseAmount())||"0".equals(mData.get(position).getSyxList().get(1).getChooseAmount()))
            {
                tv_chooseAmount2.setVisibility(View.GONE);
            }
            else
            {
                tv_chooseAmount2.setVisibility(View.VISIBLE);
            }
            tv_franchiseFlag2.setText("不计免赔");
            if ("1".equals(mData.get(position).getSyxList().get(1).getFranchiseFlag()))
            {
                tv_franchiseFlag2.setVisibility(View.VISIBLE);
            }
            else
            {
                tv_franchiseFlag2.setVisibility(View.GONE);
            }
        }
        if (mData.get(position).getSyxList().size()>2)
        {
            ll_item3.setVisibility(View.VISIBLE);
            v_xx3.setVisibility(View.VISIBLE);
            tv_itemName3.setText(mData.get(position).getSyxList().get(2).getItemName());
            tv_chooseAmount3.setText(mData.get(position).getSyxList().get(2).getChooseAmount());
            if ("1".equals(mData.get(position).getSyxList().get(2).getChooseAmount())||"0".equals(mData.get(position).getSyxList().get(2).getChooseAmount()))
            {
                tv_chooseAmount3.setVisibility(View.GONE);
            }
            else
            {
                tv_chooseAmount3.setVisibility(View.VISIBLE);
            }
            tv_franchiseFlag3.setText("不计免赔");
            if ("1".equals(mData.get(position).getSyxList().get(2).getFranchiseFlag()))
            {
                tv_franchiseFlag3.setVisibility(View.VISIBLE);
            }
            else
            {
                tv_franchiseFlag3.setVisibility(View.GONE);
            }
        }
        if (mData.get(position).getSyxList().size()>3)
        {
            ll_item4.setVisibility(View.VISIBLE);
            v_xx4.setVisibility(View.VISIBLE);
            tv_itemName4.setText(mData.get(position).getSyxList().get(3).getItemName());
            tv_chooseAmount4.setText(mData.get(position).getSyxList().get(3).getChooseAmount());
            if ("1".equals(mData.get(position).getSyxList().get(3).getChooseAmount())||"0".equals(mData.get(position).getSyxList().get(3).getChooseAmount()))
            {
                tv_chooseAmount4.setVisibility(View.GONE);
            }
            else
            {
                tv_chooseAmount4.setVisibility(View.VISIBLE);
            }
            tv_franchiseFlag4.setText("不计免赔");
            if ("1".equals(mData.get(position).getSyxList().get(3).getFranchiseFlag()))
            {
                tv_franchiseFlag4.setVisibility(View.VISIBLE);
            }
            else
            {
                tv_franchiseFlag4.setVisibility(View.GONE);
            }
        }
        if (mData.get(position).getSyxList().size()>4)
        {
            ll_item5.setVisibility(View.VISIBLE);
            v_xx5.setVisibility(View.VISIBLE);
            tv_itemName5.setText(mData.get(position).getSyxList().get(4).getItemName());
            tv_chooseAmount5.setText(mData.get(position).getSyxList().get(4).getChooseAmount());
            if ("1".equals(mData.get(position).getSyxList().get(4).getChooseAmount())||"0".equals(mData.get(position).getSyxList().get(4).getChooseAmount()))
            {
                tv_chooseAmount5.setVisibility(View.GONE);
            }
            else
            {
                tv_chooseAmount5.setVisibility(View.VISIBLE);
            }
            tv_franchiseFlag5.setText("不计免赔");
            if ("1".equals(mData.get(position).getSyxList().get(4).getFranchiseFlag()))
            {
                tv_franchiseFlag5.setVisibility(View.VISIBLE);
            }
            else
            {
                tv_franchiseFlag5.setVisibility(View.GONE);
            }
        }
        //tv_price.setText("¥"+totalPrice);
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
