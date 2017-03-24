package com.bb.hbx.activitiy;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/24.
 *实名认证审核中页面
 */

public class RealNameAuthenticatingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_realnameauthening;
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
