package com.bb.hbx.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;

import com.bb.hbx.activitiy.InfoActivity;
import com.bb.hbx.activitiy.SearchActivity;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.base.m.HomeModle;
import com.bb.hbx.base.p.HomePresenter;
import com.bb.hbx.base.v.HomeContract;
import com.bb.hbx.bean.BKItem;
import com.bb.hbx.bean.BannerBean;
import com.bb.hbx.bean.BobaoItem;


import com.bb.hbx.bean.ProductItem;
import com.bb.hbx.bean.ProductListBean;
import com.bb.hbx.bean.Special;
import com.bb.hbx.cans.Can;
import com.bb.hbx.interfaces.OnItemClickListener;
import com.bb.hbx.interfaces.OnItemInsureTypeClickListener;
import com.bb.hbx.provide.BKItemProvide;
import com.bb.hbx.provide.BKchildItemProvide;
import com.bb.hbx.provide.BannerProvide;
import com.bb.hbx.provide.BobaoProvide;
import com.bb.hbx.provide.JxItemProvide;
import com.bb.hbx.provide.ModleItemProvide;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.widget.freshlayout.OnPullListener;
import com.bb.hbx.widget.freshlayout.RefreshLayout;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bb.hbx.widget.multitype.data.Item;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/12/20.
 */

public class HomeFragment extends BaseFragment<HomePresenter, HomeModle> implements HomeContract.View, View.OnClickListener {


    @BindView(R.id.tv_messagecount)
    TextView tv_messagecount;

    @BindView(R.id.iv_xx)
    ImageView iv_xx;

    @BindView(R.id.refresh)
    RefreshLayout refresh;


    @BindView(R.id.lin_bg)
    LinearLayout rel_tool;

    @BindView(R.id.lin_search)
    LinearLayout lin_search;


    @BindView(R.id.list)
    RecyclerView rc_list;

    private GridLayoutManager layoutManager;

    private MultiTypeAdapter adapter;

    private float mDistanceY;

    private float endOffset;


    private BannerProvide bannerProvide;

    Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {


        refresh.setNeedLoadMore(false);
        refresh.setView(rel_tool);
        iv_xx.setImageResource(R.drawable.message2);
        rel_tool.getBackground().mutate().setAlpha(0);//toolbar透明度初始化为0
        layoutManager = new GridLayoutManager(getActivity(), 5);


        layoutManager.setSpanSizeLookup(mPresenter.getSpanSizeLookup());
        rc_list.setLayoutManager(layoutManager);

        endOffset = getResources().getDimensionPixelOffset(R.dimen.y500) -
                rel_tool.getHeight();
        rc_list.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //滑动的距离
                mDistanceY += dy;

                if (mDistanceY == 0) {
                    rel_tool.getBackground().mutate().setAlpha(0);
                    iv_xx.setImageResource(R.drawable.message2);
                } else if (mDistanceY > 0 && mDistanceY < endOffset) {
                    float precent = mDistanceY / endOffset;
                    int alpha = Math.round(precent * 255);
                    rel_tool.getBackground().mutate().setAlpha(alpha);
                    if (alpha > 125) {
                        iv_xx.setImageResource(R.drawable.message_zhuse);
                        lin_search.setBackgroundResource(R.drawable.shape_alpha_a6);
                    } else {
                        iv_xx.setImageResource(R.drawable.message2);
                        lin_search.setBackgroundResource(R.drawable.shape_alpha_white);
                    }
                } else if (mDistanceY >= endOffset) {
                    rel_tool.getBackground().mutate().setAlpha(255);

                }

            }
        });

        lin_search.setOnClickListener(this);
        iv_xx.setOnClickListener(this);

    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        adapter = new MultiTypeAdapter();
        adapter.applyGlobalMultiTypePool();
        boolean isPlaywhile = false;
        if (MyApplication.versionInfo != null) {
            isPlaywhile = MyApplication.versionInfo.getLoop() == 1 ? true :
                    false;
        }
        bannerProvide = new BannerProvide(isPlaywhile);
        ModleItemProvide modleItemProvide = new ModleItemProvide();
        BKItemProvide bkItemProvide = new BKItemProvide();
        adapter.register(BannerBean.class, bannerProvide);
        //adapter.register(ProductItem.class, new ModleItemProvide());
        adapter.register(ProductItem.class, modleItemProvide);
        adapter.register(BobaoItem.class, new BobaoProvide());
        //adapter.register(BKItem.class, new BKItemProvide());
        adapter.register(BKItem.class, bkItemProvide);
        adapter.register(ProductListBean.class, new BKchildItemProvide(getActivity()));
        adapter.register(Special.class, new JxItemProvide(getActivity()));
        rc_list.setAdapter(adapter);
        adapter.setItems(mPresenter.getListItems());
        refresh.setOnPullListener(new OnPullListener() {
            @Override
            public void onRefresh() {
                mPresenter.getHomePageInfo();
            }

            @Override
            public void onLoadMore() {
                refresh.stopLoadMore(true);
            }
        });


        mPresenter.getHomePageInfo();

        if (Can.hasLoginedMethod()) {//未登录
            mPresenter.getMsgs();
            tv_messagecount.setVisibility(View.VISIBLE);
        } else {
            tv_messagecount.setVisibility(View.INVISIBLE);
        }

        //点击产品首页不同保险分类,切换至商城fragment
        modleItemProvide.setmItemInsureTypeClickListener(new OnItemInsureTypeClickListener() {
            @Override
            public void onMyItemInsureTypeClickListener(String typeId) {
                Intent intent = new Intent();
                intent.putExtra("flag",0);
                intent.setAction("com.insuretype");
                intent.putExtra("typeId",typeId);
                mContext.sendBroadcast(intent);
            }
        });

        bkItemProvide.setmItemClickListener(new OnItemClickListener() {
            @Override
            public void onMyItemClickListener(int position) {
                Intent intent = new Intent();
                intent.putExtra("flag",1);
                intent.setAction("com.insuretype");
                intent.putExtra("position",position);
                mContext.sendBroadcast(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_search:
                //AppManager.getInstance().showActivity(SearchActivity.class, null);

                AppManager.getInstance().showActivity(SearchActivity.class, null);
                break;
            case R.id.iv_xx:
                AppManager.getInstance().showActivity(InfoActivity.class,null);
                break;
        }
    }


    @Override
    public void getfreshListData(List<Item> items) {

    }

    @Override
    public void notfiy() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void stopRefresh() {
        refresh.stopRefresh(true);
    }

    @Override
    public void setMsgCount(int count) {
        tv_messagecount.setVisibility(View.VISIBLE);
        if (count == 0) {
            tv_messagecount.setVisibility(View.GONE);
        } else if (count > 9) {
            tv_messagecount.setText("9+");
        } else {
            tv_messagecount.setText(count + "");
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        }
    }
}
