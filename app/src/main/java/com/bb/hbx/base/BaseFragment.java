package com.bb.hbx.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bb.hbx.activitiy.login.LoginContract;
import com.bb.hbx.base.m.BaseModel;
import com.bb.hbx.base.p.BasePresenter;
import com.bb.hbx.base.v.BaseView;
import com.bb.hbx.utils.InstanceUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment基础类
 * Created by fancl
 */

public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment {


    public P mPresenter;
    public Context mContext;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mPresenter = InstanceUtil.getInstance(this, 0);
        if (this instanceof BaseView && mPresenter != null)
            mPresenter.setVM(this, InstanceUtil.getInstance(this, 1));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdate(savedInstanceState);
    }


    public void onStart() {
        super.onStart();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetached();

    }

    /**
     * 布局加载
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 数据填充
     */

    public abstract void initView();

    protected abstract void initdate(Bundle savedInstanceState);

}
