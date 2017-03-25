package com.bb.hbx.activitiy;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.base.BaseActivity;

import butterknife.BindView;

public class RealNameFinishActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back_iv)
    ImageView back_iv;

    @BindView(R.id.name_tv)
    TextView name_tv;

    @BindView(R.id.idCard_et)
    TextView idCard_et;

    private String realName = "";
    private String idNo = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_real_name_finish;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_iv.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        realName = intent.getStringExtra("audit_realname");
        idNo = intent.getStringExtra("audit_idno");

        name_tv.setText(realName);
        idCard_et.setText(idNo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_iv:
                finish();
                break;
            default:
                break;
        }
    }
}
