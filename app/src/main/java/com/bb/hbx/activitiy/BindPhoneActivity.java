package com.bb.hbx.activitiy;

import android.content.Intent;
import android.util.Log;
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
import com.bb.hbx.bean.MessageCodeBean;
import com.bb.hbx.utils.CheckPhoneNumUtils;
import com.bb.hbx.widget.CountDownTextView;

import butterknife.BindView;
import retrofit2.Call;

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {

    private String oldSmsCode;          //老手机的验证码(用于使用手机验证码的方式修改绑定手机)
    private String newPhoneNum;         //新的手机号
    private MessageCodeBean codeBean;

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.getcode_tv)
    CountDownTextView getcode_tv;

    @BindView(R.id.verify_tv)
    TextView verify_tv;

    @BindView(R.id.phone_et)
    EditText phone_et;

    @BindView(R.id.code_et)
    EditText code_et;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        oldSmsCode = intent.getStringExtra("oldSmsCode");
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        getcode_tv.setOnClickListener(this);
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
            case R.id.getcode_tv:
                newPhoneNum = phone_et.getText().toString().trim();
                if (newPhoneNum != null && isPhoneNum(newPhoneNum)) {
                    getSmsCode();
                    getcode_tv.startTime();
                } else {
                    Toast.makeText(getApplicationContext(),"您输入的手机号码错误，请重新输入!",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.verify_tv:
                pushAndCheckVerify();
                showTip("确定");
                break;
            default:
                break;
        }
    }

    /**
     * 判断输入的是否是手机号码
     *
     * @param phoneNum
     * @return
     */
    public boolean isPhoneNum(String phoneNum) {
        return CheckPhoneNumUtils.isMobile(phoneNum);
    }

    /**
     * 提交并验证新老验证码
     */
    public void pushAndCheckVerify() {
        String newSmsCode = code_et.getText().toString().trim();
        if (newPhoneNum != null && newSmsCode != null) {
            ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
            Call call = service.updateMobile(MyApplication.user.getUserId(), oldSmsCode, null, newPhoneNum, newSmsCode);
            call.enqueue(new PostCallback() {
                @Override
                public void successCallback(Result_Api api) {
                    if (api.getOutput() != null) {
                        Log.i("ZXY", "BindPhoneActivity.successCallback.api:" + api.getOutput().toString());
                    }
                }

                @Override
                public void failCallback() {

                }
            });
        }
    }

    /**
     * 获取短信验证码 新手机号，验证类型 15
     */
    public void getSmsCode() {

        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getVerifyCode("1", newPhoneNum, "15");
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getOutput() instanceof MessageCodeBean) {
                    codeBean = (MessageCodeBean) api.getOutput();
                }
            }

            @Override
            public void failCallback() {

            }
        });
    }
}
