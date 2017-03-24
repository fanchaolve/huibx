package com.bb.hbx.activitiy;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/24.
 * 实名认证审核未通过页面
 */

public class HaveNotRealNameAuthenticationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_comment)
    TextView tv_comment;

    @BindView(R.id.tv_confim)
    TextView tv_confim;

    private String comment = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_notrealnameauthen;
    }

    @Override
    public void initView() {
        tv_comment.setText(comment);
    }

    @Override
    public void initListener() {
        toolbar.setOnClickListener(this);
        tv_confim.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        comment = intent.getStringExtra("audit_comment");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                finish();
                break;
            case R.id.tv_confim:
                startActivity(new Intent(this,RealNameIdentifyActivity.class));
                finish();
                break;
        }
    }
}
