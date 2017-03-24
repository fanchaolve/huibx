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
import com.bb.hbx.base.m.ActivitModel;
import com.bb.hbx.base.p.ActivitPresenter;
import com.bb.hbx.base.v.ActivitContract;
import com.bb.hbx.bean.ActivitBean;
import com.bb.hbx.emus.DataLoadDirection;
import com.bb.hbx.provide.ActivitProvide;
import com.bb.hbx.widget.DottedLineItemDecoration;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import butterknife.BindView;


/**
 * Created by fancl
 * 优选活动.
 */

public class ActivitFragment extends BaseFragment<ActivitPresenter, ActivitModel> implements ActivitContract.View {


    private final String TAG = ActivitFragment.class.getSimpleName();

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

    Context mContext;

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
        LinearLayoutManager manager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rl_view.setLayoutManager(manager);
        adapter = new MultiTypeAdapter();
        adapter.applyGlobalMultiTypePool();
        adapter.register(ActivitBean.class, new ActivitProvide(getActivity()));
        rl_view.setAdapter(adapter);
        rl_view.addItemDecoration(new DottedLineItemDecoration());
        /*refresh.setOnPullListener(new OnPullListener() {
            @Override
            public void onRefresh() {
                mPresenter.getAdsList(DataLoadDirection.Refresh);
            }

            @Override
            public void onLoadMore() {
                mPresenter.getAdsList(DataLoadDirection.LoadMore);
            }
        });*/
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                mPresenter.getAdsList(DataLoadDirection.Refresh);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                mPresenter.getAdsList(DataLoadDirection.LoadMore);
            }
        });
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        adapter.setItems(mPresenter.getList());
        mPresenter.getAdsList(DataLoadDirection.Refresh);
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
        refresh_layout.setVisibility(View.VISIBLE);
        iv_progress.setVisibility(View.GONE);
    }

    @Override
    public void notfiy() {
        adapter.notifyDataSetChanged();
    }

}
