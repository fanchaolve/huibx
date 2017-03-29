package com.bb.hbx.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/13.
 * 支付返回页面
 */

public class SuccessOrderActivity extends BaseActivity {


    private String msg = "";
    private int flag = 0;

    @BindView(R.id.tv_success)
    TextView tv_success;

    @BindView(R.id.tv_msg)
    TextView tv_msg;

    @Override
    public int getLayoutId() {
        return R.layout.activit_success;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.containsKey("msg")) {
                msg = (String) bundle.getSerializable("msg");
            }

            if (bundle.containsKey("ret_code")) {
                flag = bundle.getInt("ret_code");
            }
        }

        tv_msg.setText(msg + "");
        tv_success.setText(flag == 1 ? "成功" : "失败");
    }
}
