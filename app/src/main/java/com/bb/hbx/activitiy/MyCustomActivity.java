package com.bb.hbx.activitiy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MainActivity;
import com.bb.hbx.R;
import com.bb.hbx.adapter.MyCustomAdapter;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.bean.InsuredInfolBean;
import com.bb.hbx.cans.Can;
import com.bb.hbx.fragment.MyCustomContentFragment;
import com.bb.hbx.widget.MoreDailogInCustom;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;

public class MyCustomActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @BindView(R.id.more_layout)
    RelativeLayout more_layout;

    @BindView(R.id.phone_iv)
    ImageView phone_iv;

    @BindView(R.id.custom_tv)
    TextView custom_tv;

    private String[] title = new String[]{"保单记录", "赠险记录", "客户资料"};
    private ArrayList<MyCustomContentFragment> fragmentList = new ArrayList<>();
    private MyCustomAdapter adapter;

    public static InsuredInfolBean insuredInfolBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_custom;
    }

    @Override
    public void initView() {
        int code = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (code == -1) {
            //授权失败
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE}, 201);
        } else {
            //授权成功
            isFlag = true;
        }
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        more_layout.setOnClickListener(this);
        phone_iv.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        Bundle bundleBean = intent.getBundleExtra("insuredInfolBean");
        insuredInfolBean = bundleBean.getParcelable("insuredInfolBean");
        Can.myCustomFragmentList = Can.getFragmentListInMyCustom();
        custom_tv.setText(insuredInfolBean.getInsuredName());
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabTextColors(Color.GRAY, Color.BLACK);
        for (int i = 0; i < title.length; i++) {
            MyCustomContentFragment fragment = new MyCustomContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        adapter = new MyCustomAdapter(getSupportFragmentManager(), fragmentList, title);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        setIndicator(this, tablayout, 28, 28);
    }

    private boolean isFlag = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.more_layout:
                MoreDailogInCustom moreDialog = new MoreDailogInCustom(this);
                moreDialog.show();
                break;
            case R.id.phone_iv:
                requestPermission();
                break;
            default:
                break;
        }
    }

    /**
     * 申请权限
     */
    private void requestPermission() {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
                return;
            } else {
                callPhone();
            }
        } else {
            callPhone();
        }
    }

    /**
     * 打电话
     */
    private void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + insuredInfolBean.getMobile()));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
