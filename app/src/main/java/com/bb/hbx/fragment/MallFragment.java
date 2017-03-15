package com.bb.hbx.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bb.hbx.R;
import com.bb.hbx.adapter.BasePageAdapter;
import com.bb.hbx.adapter.MallPageAdapter;
import com.bb.hbx.base.BaseFragment;
import com.bb.hbx.base.m.MallModel;
import com.bb.hbx.base.p.MallPresenter;
import com.bb.hbx.base.v.MallContract;
import com.bb.hbx.bean.TypeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by facnl .
 * 商城
 */

public class MallFragment extends BaseFragment<MallPresenter, MallModel> implements MallContract.View {


    private final static String TAG = MallFragment.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    Context mContext;
    CurrentFragmentReceiver receiver;
    private List<String> tabsTitle=new ArrayList<>();

    private BasePageAdapter adapter;

    private static MallFragment fragment;
    public static MallFragment getInstance()
    {
        if (fragment==null)
        {
            fragment=new MallFragment();
        }
        return fragment;
    }

    public MallFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    public void initView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "haha");
            }
        });

        initReceiver();
    }

    //注册广播接收者
    private void initReceiver() {
        receiver = new CurrentFragmentReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.currentfragment");
        mContext.registerReceiver(receiver,filter);
    }
    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.i(TAG, "haha");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initTitle(List<TypeModel> models) {
        adapter = new MallPageAdapter(getActivity().getSupportFragmentManager(), models);

        for (int i = 0; i < models.size(); i++) {
            adapter.addFragment(new Mall_ItemFragment(models.get(i)));
            tabsTitle.add(models.get(i).getTypeId());
        }
        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mContext.unregisterReceiver(receiver);
    }

    public class CurrentFragmentReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String typeId = intent.getStringExtra("typeId");
            for (int i = 0; i < tabsTitle.size(); i++) {
                if (typeId.equals(tabsTitle.get(i)))
                {
                    viewpager.setCurrentItem(i);
                    break;
                }
            }
        }
    }
}
