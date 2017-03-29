package com.bb.hbx.activitiy;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;
import com.bb.hbx.cans.Can;

import butterknife.BindView;

public class PayPwdActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.fixPayPwd_layout)
    RelativeLayout fixPayPwd_layout;
    @BindView(R.id.forgetPayPwd_layout)
    RelativeLayout forgetPayPwd_layout;
    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_pwd;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        fixPayPwd_layout.setOnClickListener(this);
        forgetPayPwd_layout.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.fixPayPwd_layout:
                //showTip("修改");
                intent.setClass(this,CheckIdentifyActivity.class);
                startActivity(intent);
                break;
            case R.id.forgetPayPwd_layout:
                //showTip("忘记");
                intent.setClass(this,CheckIdentifyInForgerActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
