package com.bb.hbx.activitiy;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.hbx.MyApplication;
import com.bb.hbx.R;
import com.bb.hbx.api.ApiService;
import com.bb.hbx.api.PostCallback;
import com.bb.hbx.api.Result_Api;
import com.bb.hbx.api.RetrofitFactory;
import com.bb.hbx.base.BaseActivity;

import butterknife.BindView;
import retrofit2.Call;

public class CheckIdentifyUnderPwdActivity extends BaseActivity implements View.OnClickListener {
    private String pwd;
    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.pwd_et)
    EditText pwd_et;

    @BindView(R.id.verify_tv)
    TextView verify_tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_identify_under_pwd;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        verify_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.verify_tv:
                pwd = pwd_et.getText().toString().trim();
                if (pwd != null) {
                    checkPwdFromService(pwd);
                } else {
                    Toast.makeText(getApplicationContext(), "请输入密码！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 请求服务器验证密码
     *
     * @param pwd
     */
    public void checkPwdFromService(final String pwd) {
        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.checkVerifyPwd(MyApplication.user.getUserId(), pwd);
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
                if (api.isSuccess()) {
                    Toast.makeText(getApplicationContext(),"密码输入正确",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CheckIdentifyUnderPwdActivity.this, BindPhoneActivity.class);
                    intent.putExtra("pwd",pwd);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "密码错误,请重新输入！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failCallback() {

            }
        });
    }
}
