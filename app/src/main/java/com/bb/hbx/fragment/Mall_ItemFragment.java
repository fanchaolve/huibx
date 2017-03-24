package com.bb.hbx.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.activitiy.FilterActivity;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.base.m.Mall_itemModel;
import com.bb.hbx.base.p.Mall_ItemPresenter;
import com.bb.hbx.base.v.Mall_ItemContract;
import com.bb.hbx.bean.Product;
import com.bb.hbx.bean.TypeModel;
import com.bb.hbx.emus.DataLoadDirection;
import com.bb.hbx.provide.MallAllProvide;
import com.bb.hbx.provide.MallCarProvide;
import com.bb.hbx.utils.AppManager;
import com.bb.hbx.utils.Constants;
import com.bb.hbx.widget.ConditionLayout;
import com.bb.hbx.widget.freshlayout.OnPullListener;
import com.bb.hbx.widget.freshlayout.RefreshLayout;
import com.bb.hbx.widget.multitype.MultiTypeAdapter;
import com.bb.hbx.widget.multitype.data.Item;
import com.bumptech.glide.Glide;


import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/22.
 */

public class Mall_ItemFragment extends BaseFragment<Mall_ItemPresenter, Mall_itemModel>
        implements Mall_ItemContract.View {


    private final String TAG = Mall_ItemFragment.class.getSimpleName();

    @BindView(R.id.rl_view)
    RecyclerView rl_view;


    @BindView(R.id.refresh)
    RefreshLayout refresh;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.iv_progress)
    ImageView iv_progress;
    private MultiTypeAdapter adapter;

    private int pageType;


    private int conditType;//筛选条件


    private ConditionLayout.STATE state = ConditionLayout.STATE.DEFAULT;
    Context mContext;

    @BindView(R.id.cl_condit)
    ConditionLayout cl_condit;


    private TypeModel model;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    public Mall_ItemFragment(TypeModel model) {
        this.model = model;
    }

    public Mall_ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            pageType = bundle.getInt(Constants.TYPE);
            Log.i(TAG, "pageType:" + pageType);
            setRetainInstance(true);

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mall_item;
    }

    @Override
    public void initView() {

        cl_condit.setSate(state);
        cl_condit.setListener(new ConditionLayout.ConditionListener() {
            @Override
            public void priceListener(ConditionLayout.STATE operat) {
                state = operat;
                if (ConditionLayout.STATE.PRICE_DOWN == operat) {
                    mPresenter.setSortCode(-1);
                } else if (ConditionLayout.STATE.PRICE_UP == operat) {
                    mPresenter.setSortCode(1);
                } else {
                    mPresenter.setSortCode(0);
                }
                mPresenter.getProducts(DataLoadDirection.Refresh);

            }

            @Override
            public void saleListener(ConditionLayout.STATE operat) {
                state = operat;
                if (ConditionLayout.STATE.SALE_DOWN == operat) {
                    mPresenter.setSortCode(-2);
                } else if (ConditionLayout.STATE.SALE_UP == operat) {
                    mPresenter.setSortCode(2);
                } else {
                    mPresenter.setSortCode(0);
                }
                mPresenter.getProducts(DataLoadDirection.Refresh);

            }

            @Override
            public void filterListener() {
                Bundle bundle = new Bundle();
                bundle.putSerializable("title", model);
                AppManager.getInstance().showActivity(FilterActivity.class, bundle);

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rl_view.setLayoutManager(manager);
        adapter = new MultiTypeAdapter();
        adapter.applyGlobalMultiTypePool();
        if (model != null && "车险".equalsIgnoreCase(model.getTypeName())) {
            cl_condit.setVisibility(View.GONE);
            adapter.register(Product.class,new MallCarProvide(getActivity()));
        }else {
            adapter.register(Product.class, new MallAllProvide(getActivity()));
        }

        rl_view.setAdapter(adapter);

        refresh.setOnPullListener(new OnPullListener() {
            @Override
            public void onRefresh() {
                mPresenter.getProducts(DataLoadDirection.Refresh);
            }

            @Override
            public void onLoadMore() {
                mPresenter.getProducts(DataLoadDirection.LoadMore);
            }
        });


    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        adapter.setItems(mPresenter.getList());
        mPresenter.getProducts(DataLoadDirection.Refresh);
        Glide.with(mContext).load(R.drawable.loading).into(iv_progress);
    }


    @Override
    public void notfiy() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public TypeModel getTypeModel() {
        return model;
    }

    @Override
    public void stopRefresh() {
        if (refresh!=null)
        {
            refresh.stopRefresh(true);
        }
    }

    @Override
    public void stopLoadMore() {
        if (refresh!=null)
        {
            refresh.stopLoadMore(true);
        }
    }

    @Override
    public void stopLoading() {
        if (iv_progress!=null)
        {
            iv_progress.setVisibility(View.GONE);
        }
        if (ll_content!=null)
        {
            ll_content.setVisibility(View.VISIBLE);
        }
    }
}
