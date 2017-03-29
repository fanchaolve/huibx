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
import com.bb.hbx.cans.Can;
import com.bb.hbx.widget.CountDownTextView;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckIdentifyInForgerActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.back_layout)
    RelativeLayout back_layout;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.getcode_tv)
    CountDownTextView getcode_tv;
    @BindView(R.id.code_et)
    EditText code_et;
    @BindView(R.id.nextStep_tv)
    TextView nextStep_tv;

    String mobile="";

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_identify_in_forger;
    }

    @Override
    public void initView() {
        mobile = MyApplication.user.getMobile();
        phone_tv.setText(mobile);
    }

    @Override
    public void initListener() {
        back_layout.setOnClickListener(this);
        getcode_tv.setOnClickListener(this);
        nextStep_tv.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_layout:
                finish();
                break;
            case R.id.getcode_tv:
                getcode_tv.startTime();
                ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call=service.getVerifyCode("1",mobile,"13");
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                break;
            case R.id.nextStep_tv:
                String code = code_et.getText().toString().trim();
                ApiService service1 = RetrofitFactory.getINSTANCE().create(ApiService.class);
                Call call1 = service1.verifyMobile(MyApplication.user.getUserId(), code);
                call1.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Result_Api api = (Result_Api) response.body();
                        if (api != null) {
                            if (api.isSuccess()) {
                                Intent intent = new Intent(mContext, FixPayPwdActivity.class);
                                intent.putExtra("flag", Can.FORGET_PWD);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(mContext,"验证码错误!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(mContext,"网络异常!",Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            default:
                break;
        }
    }
}
