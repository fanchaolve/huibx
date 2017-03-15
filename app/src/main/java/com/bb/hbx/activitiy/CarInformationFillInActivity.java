package com.bb.hbx.activitiy;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.utils.AppManager;

import butterknife.BindView;

/**
 * Created by fancl
 * 车险 信息填写
 */

public class CarInformationFillInActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @Override
    public int getLayoutId() {
        return R.layout.carinfomationfillin;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        toolbar.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
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
            case R.id.tv_submit:
                AppManager.getInstance().showActivity(SelectCarActivity.class, null);
                break;
            default:
                break;
        }
    }
}
