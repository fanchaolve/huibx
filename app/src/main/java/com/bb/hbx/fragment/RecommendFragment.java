package com.bb.hbx.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.base.m.RecommendModel;
import com.bb.hbx.base.p.RecommendPresenter;
import com.bb.hbx.base.v.RecommendContract;
import com.bb.hbx.bean.ProductListBean;
import com.bb.hbx.emus.DataLoadDirection;
import com.bb.hbx.provide.RecommendProvide;
import com.bb.hbx.utils.TimeUtils;
import com.bb.hbx.widget.DottedLineItemDecoration;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bb.hbx.widget.multitype.data.Item;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by fancl
 * 爆款推荐的页面
 */

public class RecommendFragment extends BaseFragment<RecommendPresenter,RecommendModel>
        implements RecommendContract.View {

    private final String TAG = RecommendFragment.class.getSimpleName();


    @BindView(R.id.refresh_layout)
    RelativeLayout refresh_layout;
    @BindView(R.id.rl_view)
    RecyclerView rl_view;

    /*@BindView(R.id.refresh)
    RefreshLayout refresh;*/
    @BindView(R.id.refresh)
    PullToRefreshScrollView refresh;

    @BindView(R.id.iv_progress)
    ImageView iv_progress;

    private MultiTypeAdapter adapter;


    private List<Item> items;

    Context mContext;
    boolean isFirst=true;

    ILoadingLayout layoutProxy;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {

        //显示时间
        layoutProxy = refresh.getLoadingLayoutProxy(true, true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rl_view.setLayoutManager(manager);
        adapter = new MultiTypeAdapter();
        adapter.applyGlobalMultiTypePool();
        adapter.register(ProductListBean.class, new RecommendProvide(getActivity()));
        rl_view.setAdapter(adapter);
        rl_view.addItemDecoration(new DottedLineItemDecoration());
        /*refresh.setOnPullListener(new OnPullListener() {
            @Override
            public void onRefresh() {
                mPresenter.getSpecialProductList(DataLoadDirection.Refresh);
            }

            @Override
            public void onLoadMore() {
                mPresenter.getSpecialProductList(DataLoadDirection.LoadMore);
            }
        });*/
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                layoutProxy.setLastUpdatedLabel(TimeUtils.getLastTime());
                mPresenter.getSpecialProductList(DataLoadDirection.Refresh);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                mPresenter.getSpecialProductList(DataLoadDirection.LoadMore);
            }
        });
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        adapter.setItems(mPresenter.getList());
        mPresenter.getSpecialProductList(DataLoadDirection.Refresh);
        Glide.with(mContext).load(R.drawable.loading).into(iv_progress);
    }

    @Override
    public void stopRefresh() {
        //refresh.stopRefresh(true);
        if (refresh.isRefreshing())
        {
            refresh.onRefreshComplete();
        }
    }

    @Override
    public void stopLoadMore() {
        //refresh.stopLoadMore(true);
        if (refresh.isRefreshing())
        {
            refresh.onRefreshComplete();
        }
    }

    @Override
    public void stopLoading() {
        iv_progress.setVisibility(View.GONE);
        refresh_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void notfiy() {
        adapter.notifyDataSetChanged();
    }
}
