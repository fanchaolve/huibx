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
import com.bb.hbx.bean.MessageCodeBean;
import com.bb.hbx.widget.CountDownTextView;

import butterknife.BindView;
import retrofit2.Call;

public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener {

    private String oldPhoneNum;
    private MessageCodeBean codeBean;

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;

    @BindView(R.id.code_et)
    EditText code_et;

    @BindView(R.id.getcode_tv)
    CountDownTextView getcode_tv;

    @BindView(R.id.verify_tv)
    TextView verify_tv;

    @BindView(R.id.info_tv)
    TextView info_tv;

    @BindView(R.id.phone_tv)
    TextView phone_tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_phone;
    }

    @Override
    public void initView() {
        oldPhoneNum = MyApplication.user.getMobile();
        phone_tv.setText(oldPhoneNum);
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        getcode_tv.setOnClickListener(this);
        verify_tv.setOnClickListener(this);
        info_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.getcode_tv:
                if (phone_tv.getText() != null) {
                    getcode_tv.startTime();
                    getSmsCode();
                }

                break;
            case R.id.verify_tv:
                //showTip("确定");
//                String verifyCode = code_et.getText().toString().trim();
//                String oldSmsCode;
//                if (codeBean != null) {
//                    oldSmsCode = codeBean.getSmsCode();
//                    if (verifyCode != null && verifyCode.equals(oldSmsCode)) {
//                        intent.putExtra("oldSmsCode", oldSmsCode);
//                        intent.setClass(MyApplication.getAppContext(), BindPhoneActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "验证码错误！", Toast.LENGTH_SHORT).show();
//                    }
//                }
                String smsCode = "";
                smsCode = code_et.getText().toString().trim();
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call = service.verifyMobile(MyApplication.user.getUserId(), smsCode);
                call.enqueue(new PostCallback() {
                    @Override
                    public void successCallback(Result_Api api) {
                        if (api != null) {
                            if (api.isSuccess()) {
                                startActivity(new Intent(mContext,BindPhoneActivity.class));
                                finish();
                            } else {
                                Toast.makeText(mContext,"验证码错误！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void failCallback() {
                        Toast.makeText(mContext,"网络加载失败，请稍后再试！",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.info_tv:
                //showTip("提示");
                intent.setClass(this, CheckIdentifyUnderPwdActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 获取短信验证码 老手机号，验证类型 13
     */
    public void getSmsCode() {

        ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
        Call call = service.getVerifyCode("1", oldPhoneNum, "13");
        call.enqueue(new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {
//                if (api.getOutput() instanceof MessageCodeBean) {
//                    codeBean = (MessageCodeBean) api.getOutput();
//                }
            }

            @Override
            public void failCallback() {

            }
        });
    }
}
