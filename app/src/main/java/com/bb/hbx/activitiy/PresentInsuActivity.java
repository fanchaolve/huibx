package com.bb.hbx.activitiy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bb.hbx.R;
import com.bb.hbx.adapter.MyPresentInsuAdapter;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.cans.Can;
import com.bb.hbx.fragment.PresentInsuContentFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;

public class PresentInsuActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.topbar_layout)
    RelativeLayout topbar_layout;

    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ArrayList<PresentInsuContentFragment> fragmentList = new ArrayList<>();
    String[] title = new String[]{"可领取", "已使用", "已过期"};

    MyPresentInsuAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_present_insu;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        topbar_layout.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Can.presentInsuFragmentList = Can.getFragmentListInPresentInsu();
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabTextColors(Color.GRAY, Color.BLACK);
        for (int i = 0; i < title.length; i++) {
            PresentInsuContentFragment fragment = new PresentInsuContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        adapter = new MyPresentInsuAdapter(getSupportFragmentManager(), fragmentList, title);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tablayout.setupWithViewPager(viewPager);
        setIndicator(this, tablayout, 28, 28);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topbar_layout:
                finish();
                break;
            default:
                break;
        }
    }

    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (getDisplayMetrics(context).density * leftDip);
        int right = (int) (getDisplayMetrics(context).density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }
}
