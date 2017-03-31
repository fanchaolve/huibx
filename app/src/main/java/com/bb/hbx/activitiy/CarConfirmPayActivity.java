package com.bb.hbx.activitiy;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.widget.MyWebView;

import butterknife.BindView;

//车险 确认支付 页面
public class CarConfirmPayActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview_wv)
    MyWebView webview_wv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_confirm_pay;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {

    }
}
