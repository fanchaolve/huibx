package com.bb.hbx.activitiy;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/22.
 * 消息详情页面
 */

public class MsgDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_tittle)
    TextView tv_tittle;

    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.tv_activitytime)
    TextView tv_activitytime;

    @BindView(R.id.tv_product_text1)
    TextView tv_product_text1;

    @BindView(R.id.tv_product_text2)
    TextView tv_product_text2;

    @BindView(R.id.tv_product_text3)
    TextView tv_product_text3;

    @BindView(R.id.tv_guarantee_text1)
    TextView tv_guarantee_text1;

    @BindView(R.id.tv_guarantee_text2)
    TextView tv_guarantee_text2;

    @BindView(R.id.tv_guarantee_text3)
    TextView tv_guarantee_text3;

    @BindView(R.id.tv_guarantee_text4)
    TextView tv_guarantee_text4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_msgdetails;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        toolbar.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                finish();
                break;
        }
    }
}
